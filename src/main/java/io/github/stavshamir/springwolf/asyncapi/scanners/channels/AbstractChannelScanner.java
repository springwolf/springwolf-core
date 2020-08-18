package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.google.common.collect.Maps;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ComponentsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
public abstract class AbstractChannelScanner<T extends Annotation> implements ChannelsScanner {

    @Autowired
    private ComponentsScanner componentsScanner;

    @Autowired
    private SchemasService schemasService;

    @Override
    public Map<String, Channel> scan() {
        String basePackage = getBasePackage();

        return componentsScanner.scanForComponents(basePackage).stream()
                .map(this::getAnnotatedMethods).flatMap(Collection::stream)
                .map(this::mapMethodToChannel)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * @return The base package containing @Component classes with listener annotations.
     */
    protected abstract String getBasePackage();

    /**
     * @return The class object of the listener annotation.
     */
    protected abstract Class<T> getListenerAnnotationClass();

    /**
     * @param annotation An instance of a listener annotation.
     * @return The channel name associated with this instance of listener annotation.
     */
    protected abstract String getChannelName(T annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing an operation binding pointed to by the the protocol binding name.
     */
    protected abstract Map<String, ? extends OperationBinding> buildOperationBinding(T annotation);

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        Class<T> annotationClass = getListenerAnnotationClass();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private Map.Entry<String, Channel> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Class<T> listenerAnnotationClass = getListenerAnnotationClass();
        T annotation = Optional.of(method.getAnnotation(listenerAnnotationClass))
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with " + listenerAnnotationClass.getName()));

        String channelName = getChannelName(annotation);

        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Class<?> payload = getPayloadType(method);
        Channel channel = buildChannel(payload, operationBinding);

        return Maps.immutableEntry(channelName, channel);
    }

    private Class<?> getPayloadType(Method method) {
        log.debug("Finding payload type for {}", method.getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Only single parameter KafkaListener methods are supported");
        }

        return parameterTypes[0];
    }

    private Channel buildChannel(Class<?> payloadType, Map<String, ? extends OperationBinding> operationBinding) {
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

        return Channel.builder()
                .subscribe(operation)
                .build();
    }

}
