// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
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
    public Stream<Map.Entry<String, ChannelObject>> process(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(methodAnnotationClass, method) != null)
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelObject> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        MethodAnnotation annotation = AnnotationUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        String channelName = bindingFactory.getChannelName(annotation);
        String operationId = channelName + "_publish_" + method.getName();
        Class<?> payload = payloadClassExtractor.extractFrom(method);

        ChannelObject channelItem = buildChannelItem(annotation, operationId, payload);

        return Map.entry(channelName, channelItem);
    }

    private ChannelObject buildChannelItem(MethodAnnotation annotation, String operationId, Class<?> payloadType) {
        MessageObject message = buildMessage(annotation, payloadType);
        Operation operation = buildOperation(annotation, operationId, message);
        return buildChannelItem(annotation, operation);
    }

    private MessageObject buildMessage(MethodAnnotation annotation, Class<?> payloadType) {
        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(annotation);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(AsyncHeaders.NOT_DOCUMENTED);

        return MessageObject.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                //                .payload(PayloadReference.fromModelName(modelName)) FIXME
                //                .headers(HeaderReference.fromModelName(headerModelName)) FIXME
                .bindings(messageBinding)
                .build();
    }

    private Operation buildOperation(MethodAnnotation annotation, String operationTitle, MessageObject message) {
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(annotation);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;

        return Operation.builder()
                .description("Auto-generated description")
                .title(operationTitle)
                //                .message(message)
                .bindings(opBinding)
                .build();
    }

    private ChannelObject buildChannelItem(MethodAnnotation annotation, Operation operation) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(annotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        return ChannelObject.builder()
                .bindings(chBinding) /*.publish(operation) FIXME*/
                .build();
    }
}
