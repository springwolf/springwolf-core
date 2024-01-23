// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleOperationsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
public class MethodLevelAnnotationOperationsScanner<MethodAnnotation extends Annotation>
        implements SimpleOperationsScanner.ClassProcessor {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final BindingFactory<MethodAnnotation> bindingFactory;
    private final PayloadClassExtractor payloadClassExtractor;
    private final SchemasService schemasService;

    @Override
    public Stream<Map.Entry<String, Operation>> process(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(methodAnnotationClass, method) != null)
                .map(this::mapMethodToOperation);
    }

    private Map.Entry<String, Operation> mapMethodToOperation(Method method) {
        log.debug("Mapping method \"{}\" to operations", method.getName());

        MethodAnnotation annotation = AnnotationUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        String channelName = bindingFactory.getChannelName(annotation);
        String operationId = channelName + "_" + OperationAction.RECEIVE + "_" + method.getName();
        Class<?> payload = payloadClassExtractor.extractFrom(method);

        Operation operation = buildOperation(annotation, payload);
        return Map.entry(operationId, operation);
    }

    private Operation buildOperation(MethodAnnotation annotation, Class<?> payloadType) {
        MessageObject message = buildMessage(annotation, payloadType);
        return buildOperation(annotation, message);
    }

    private MessageObject buildMessage(MethodAnnotation annotation, Class<?> payloadType) {
        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(annotation);
        String modelName = schemasService.registerSchema(payloadType);
        String headerModelName = schemasService.registerSchema(AsyncHeaders.NOT_DOCUMENTED);
        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(payloadType.getName())
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(messageBinding)
                .build();

        this.schemasService.registerMessage(message);
        return message;
    }

    private Operation buildOperation(MethodAnnotation annotation, MessageObject message) {
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(annotation);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        String channelName = bindingFactory.getChannelName(annotation);

        return Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelName))
                .messages(List.of(MessageReference.toChannelMessage(channelName, message)))
                .bindings(opBinding)
                .build();
    }
}
