package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
public class MethodLevelRabbitListenerScanner implements ChannelsScanner, EmbeddedValueResolverAware {

    private static final String RABBIT_LISTENER = "RabbitListener";

    private final Class<? extends Annotation> annotationClass;
    private final AsyncApiDocket docket;
    private final ComponentsScanner componentsScanner;
    private final SchemasService schemasService;

    private StringValueResolver resolver;

    public MethodLevelRabbitListenerScanner(AsyncApiDocket docket, ComponentsScanner componentsScanner, SchemasService schemasService) {
        this.docket = docket;
        this.componentsScanner = componentsScanner;
        this.schemasService = schemasService;
        annotationClass = loadAnnotationClass();
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Annotation> loadAnnotationClass() {
        try {
            return (Class<? extends Annotation>) Class.forName(getListenerClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class: " + getListenerClassName());
        }
    }

    @Override
    public Map<String, ChannelItem> scan() {
        String basePackage = docket.getBasePackage();

        return componentsScanner.scanForComponents(basePackage).stream()
                .map(this::getAnnotatedMethods).flatMap(Collection::stream)
                .map(this::mapMethodToChannel)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), RABBIT_LISTENER);

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private String getListenerClassName() {
        return "org.springframework.amqp.rabbit.annotation.RabbitListener";
    }

    private Map.Entry<String, ChannelItem> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Annotation annotation = getRabbitListenerAnnotation(method)
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with " + getListenerClassName()));

        String channelName = getChannelName(annotation);

        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(null);
        Class<?> payload = getPayloadType(method);
        ChannelItem channel = buildChannel(payload, operationBinding);

        return Maps.immutableEntry(channelName, channel);
    }

    private String getChannelName(Annotation annotation) {
        try {
            Object queues = annotation.annotationType()
                    .getDeclaredMethod("queues")
                    .invoke(annotation, (Object[]) null);

            if (queues instanceof String[]) {
                List<String> resolvedTopics = Arrays.stream((String[]) queues)
                        .map(resolver::resolveStringValue)
                        .collect(toList());

                log.debug("Found queues: {}", String.join(", ", resolvedTopics));
                return resolvedTopics.get(0);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Map<String, ? extends OperationBinding> buildOperationBinding(Annotation annotation) {
        return ImmutableMap.of("amqp", new AMQPOperationBinding());
    }


    private Optional<Annotation> getRabbitListenerAnnotation(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation.annotationType().getName().equals(getListenerClassName()))
                .findAny();
    }

    private Class<?> getPayloadType(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        Class<?>[] parameterTypes = method.getParameterTypes();
        switch (parameterTypes.length) {
            case 0:
                throw new IllegalArgumentException("Listener methods must not have 0 parameters: " + methodName);
            case 1:
                return parameterTypes[0];
            default:
                return getPayloadType(parameterTypes, method.getParameterAnnotations(), methodName);
        }
    }

    private Class<?> getPayloadType(Class<?>[] parameterTypes, Annotation[][] parameterAnnotations, String methodName) {
        int payloadAnnotatedParameterIndex = getPayloadAnnotatedParameterIndex(parameterAnnotations);

        if (payloadAnnotatedParameterIndex == -1) {
            String msg = "Multi-parameter KafkaListener methods must have one parameter annotated with @Payload, "
                    + "but none was found: "
                    + methodName;

            throw new IllegalArgumentException(msg);
        }

        return parameterTypes[payloadAnnotatedParameterIndex];
    }

    private int getPayloadAnnotatedParameterIndex(Annotation[][] parameterAnnotations) {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            Annotation[] annotations = parameterAnnotations[i];

            try {
                Class<?> payloadAnnotationClass = Class.forName("org.springframework.messaging.handler.annotation.Payload");
                boolean hasPayloadAnnotation = Arrays.stream(annotations).anyMatch(payloadAnnotationClass::isInstance);

                if (hasPayloadAnnotation) {
                    return i;
                }
            } catch (ClassNotFoundException e) {
                log.error("Failed to load @Payload annotation class");
            }

        }

        return -1;
    }

    private ChannelItem buildChannel(Class<?> payloadType, Map<String, ? extends OperationBinding> operationBinding) {
        String modelName = schemasService.register(payloadType);

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .build();

        Operation operation = Operation.builder()
                .message(message)
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .publish(operation)
                .build();
    }
}

