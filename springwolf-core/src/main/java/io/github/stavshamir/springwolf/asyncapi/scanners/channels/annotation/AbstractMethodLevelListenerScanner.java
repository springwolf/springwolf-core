// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelMerger;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractMethodLevelListenerScanner<T extends Annotation> implements ChannelsScanner {

    private final ComponentClassScanner componentClassScanner;

    private final SchemasService schemasService;

    @Override
    public Map<String, ChannelItem> scan() {
        Set<Class<?>> components = componentClassScanner.scan();
        List<Map.Entry<String, ChannelItem>> channels = mapToChannels(components);
        return ChannelMerger.merge(channels);
    }

    private List<Map.Entry<String, ChannelItem>> mapToChannels(Set<Class<?>> components) {
        return components.stream()
                .map(this::getAnnotatedMethods)
                .flatMap(Collection::stream)
                .map(this::mapMethodToChannel)
                .collect(Collectors.toList());
    }

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
     * @return A map containing the channel binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends ChannelBinding> buildChannelBinding(T annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing an operation binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends OperationBinding> buildOperationBinding(T annotation);

    /**
     * @param annotation An instance of a listener annotation.
     * @return A map containing a message binding pointed to by the protocol binding name.
     */
    protected abstract Map<String, ? extends MessageBinding> buildMessageBinding(T annotation);

    /**
     * @param method The listener method.
     * @return The class object of the payload received by the listener.
     */
    protected abstract Class<?> getPayloadType(Method method);

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        Class<T> annotationClass = getListenerAnnotationClass();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private Map.Entry<String, ChannelItem> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Class<T> listenerAnnotationClass = getListenerAnnotationClass();
        T annotation = Optional.of(method.getAnnotation(listenerAnnotationClass))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Method must be annotated with " + listenerAnnotationClass.getName()));

        String channelName = getChannelName(annotation);

        Map<String, ? extends ChannelBinding> channelBinding = buildChannelBinding(annotation);
        Map<String, ? extends OperationBinding> operationBinding = buildOperationBinding(annotation);
        Map<String, ? extends MessageBinding> messageBinding = buildMessageBinding(annotation);
        Class<?> payload = getPayloadType(method);
        String operationId = channelName + "_publish_" + method.getName();
        ChannelItem channel = buildChannel(channelBinding, payload, operationBinding, messageBinding, operationId);

        return Map.entry(channelName, channel);
    }

    private ChannelItem buildChannel(
            Map<String, ? extends ChannelBinding> channelBinding,
            Class<?> payloadType,
            Map<String, ? extends OperationBinding> operationBinding,
            Map<String, ? extends MessageBinding> messageBinding,
            String operationId) {
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(messageBinding)
                .build();

        Operation operation = Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(message)
                .bindings(opBinding)
                .build();

        return ChannelItem.builder().bindings(chBinding).publish(operation).build();
    }
}
