package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.binding.OperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
public abstract class AbstractMethodLevelListenerScanner implements ChannelsScanner {

    @Autowired
    private ComponentsScanner componentsScanner;

    @Autowired
    private SchemasService schemasService;

    private Class<? extends Annotation> annotationClass;

    /**
     * @return The fully qualified class name of the listener annotation.
     */
    protected abstract String getListenerClassName();

    /**
     * @param annotation An instance of a listener annotation.
     * @return The channel name associated with this instance of listener annotation.
     */
    protected abstract String getChannelName(Annotation annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing an operation binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends OperationBinding> buildOperationBinding(Annotation annotation);

    @Override
    public Map<String, ChannelItem> scan() {
        annotationClass = loadAnnotationClass();

        return componentsScanner.scanForComponents().stream()
                .map(this::getAnnotatedMethods).flatMap(Collection::stream)
                .map(this::mapMethodToChannel)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    @SuppressWarnings("unchecked")
    private Class<? extends Annotation> loadAnnotationClass() {
        try {
            return (Class<? extends Annotation>) Class.forName(getListenerClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load class: " + getListenerClassName());
        }
    }

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getSimpleName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private Map.Entry<String, ChannelItem> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Annotation annotation = getListenerAnnotation(method)
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with " + annotationClass.getSimpleName()));

        String channelName = getChannelName(annotation);
        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Class<?> payload = getPayloadType(method);
        ChannelItem channel = buildChannel(payload, operationBinding);

        return Maps.immutableEntry(channelName, channel);
    }

    private Optional<Annotation> getListenerAnnotation(Method method) {
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
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
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
