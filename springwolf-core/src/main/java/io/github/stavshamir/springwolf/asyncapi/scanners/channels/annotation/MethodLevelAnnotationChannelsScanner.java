// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public class MethodLevelAnnotationChannelsScanner<MethodAnnotation extends Annotation>
        implements SimpleChannelsScanner.ClassProcessor {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final BindingFactory<MethodAnnotation> bindingFactory;
    private final PayloadClassExtractor payloadClassExtractor;
    private final SchemasService schemasService;

    @Override
    public Stream<Map.Entry<String, ChannelItem>> process(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(methodAnnotationClass, method) != null)
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelItem> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        MethodAnnotation annotation = AnnotationUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        String channelName = bindingFactory.getChannelName(annotation);
        String operationId = channelName + "_publish_" + method.getName();
        Class<?> payload = payloadClassExtractor.extractFrom(method);

        ChannelItem channelItem = buildChannelItem(annotation, operationId, payload);

        return Map.entry(channelName, channelItem);
    }

    private ChannelItem buildChannelItem(MethodAnnotation annotation, String operationId, Class<?> payloadType) {
        Message message = buildMessage(annotation, payloadType);
        Operation operation = buildOperation(annotation, operationId, message);
        return buildChannelItem(annotation, operation);
    }

    private Message buildMessage(MethodAnnotation annotation, Class<?> payloadType) {
        Map<String, ? extends MessageBinding> messageBinding = bindingFactory.buildMessageBinding(annotation);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        return Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(messageBinding)
                .build();
    }

    private Operation buildOperation(MethodAnnotation annotation, String operationId, Message message) {
        Map<String, ? extends OperationBinding> operationBinding = bindingFactory.buildOperationBinding(annotation);
        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;

        return Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(message)
                .bindings(opBinding)
                .build();
    }

    private ChannelItem buildChannelItem(MethodAnnotation annotation, Operation operation) {
        Map<String, ? extends ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(annotation);
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        return ChannelItem.builder().bindings(chBinding).publish(operation).build();
    }
}
