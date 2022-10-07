package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static io.github.stavshamir.springwolf.asyncapi.Constants.ONE_OF;
import static java.util.stream.Collectors.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClassLevelKafkaListenerScanner
        implements ChannelsScanner, EmbeddedValueResolverAware {


    private StringValueResolver resolver;

    @Autowired
    private AsyncApiDocket docket;

    @Autowired
    private SchemasService schemasService;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    public Map<String, ChannelItem> scan() {
        return docket.getComponentsScanner().scanForComponents().stream()
                .filter(this::isAnnotatedWithKafkaListener)
                .map(this::mapClassToChannel)
                .filter(Optional::isPresent).map(Optional::get)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private boolean isAnnotatedWithKafkaListener(Class<?> component) {
        return component.isAnnotationPresent(KafkaListener.class);
    }

    private Optional<Map.Entry<String, ChannelItem>> mapClassToChannel(Class<?> component) {
        log.debug("Mapping class \"{}\" to channel", component.getName());

        KafkaListener annotation = component.getAnnotation(KafkaListener.class);
        String channelName = getChannelName(annotation);
        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Set<Method> annotatedMethods = getAnnotatedMethods(component);

        if (annotatedMethods.isEmpty()) {
            return Optional.empty();
        }

        ChannelItem channelItem = buildChannel(annotatedMethods, operationBinding);
        return Optional.of(Maps.immutableEntry(channelName, channelItem));
    }

    protected String getChannelName(KafkaListener annotation) {
        List<String> resolvedTopics = Arrays.stream(annotation.topics())
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found topics: {}", String.join(", ", resolvedTopics));
        return resolvedTopics.get(0);
    }

    protected Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation) {
        String groupId = resolver.resolveStringValue(annotation.groupId());
        if (groupId == null || groupId.isEmpty()) {
            log.debug("No group ID found for this listener");
            groupId = null;
        } else {
            log.debug("Found group id: {}", groupId);
        }

        KafkaOperationBinding binding = new KafkaOperationBinding();
        binding.setGroupId(groupId);
        return ImmutableMap.of("kafka", binding);

    }

    protected Class<?> getPayloadType(Method method) {
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
            String msg = "Multi-parameter methods must have one parameter annotated with @Payload, "
                    + "but none was found: "
                    + methodName;

            throw new IllegalArgumentException(msg);
        }

        return parameterTypes[payloadAnnotatedParameterIndex];
    }

    private int getPayloadAnnotatedParameterIndex(Annotation[][] parameterAnnotations) {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            boolean hasPayloadAnnotation = Arrays.stream(annotations)
                    .anyMatch(annotation -> annotation instanceof Payload);

            if (hasPayloadAnnotation) {
                return i;
            }
        }

        return -1;
    }

    private Set<Method> getAnnotatedMethods(Class<?> component) {
        Class<KafkaHandler> annotationClass = KafkaHandler.class;
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", component.getName(), annotationClass.getName());

        return Arrays.stream(component.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private ChannelItem buildChannel(Set<Method> methods, Map<String, ? extends OperationBinding> operationBinding) {
        Operation operation = Operation.builder()
                .message(getMessageObject(methods))
                .bindings(operationBinding)
                .build();

        return ChannelItem.builder()
                .bindings(ImmutableMap.of("kafka", new KafkaChannelBinding()))
                .publish(operation)
                .build();
    }

    private Object getMessageObject(Set<Method> methods) {
        Set<Message> messages = methods.stream()
                .map(this::buildMessage)
                .collect(toSet());

        return methods.size() == 1
                ? messages.toArray()[0]
                : ImmutableMap.of(ONE_OF, messages);
    }

    private Message buildMessage(Method method) {
        Class<?> payloadType = getPayloadType(method);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        return Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .build();
    }

}
