// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AsyncAnnotationUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public abstract class AsyncAnnotationScanner<A extends Annotation> implements EmbeddedValueResolverAware {

    protected final AsyncAnnotationProvider<A> asyncAnnotationProvider;
    protected final PayloadClassExtractor payloadClassExtractor;
    protected final ComponentsService componentsService;
    protected final List<OperationBindingProcessor> operationBindingProcessors;
    protected final List<MessageBindingProcessor> messageBindingProcessors;
    protected StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    protected Stream<MethodAndAnnotation<A>> getAnnotatedMethods(Class<?> type) {
        Class<A> annotationClass = this.asyncAnnotationProvider.getAnnotation();
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationScannerUtil.findAnnotation(annotationClass, method) != null)
                .peek(method -> log.debug("Mapping method \"{}\" to channels", method.getName()))
                .flatMap(method -> AnnotationScannerUtil.findAnnotations(annotationClass, method).stream()
                        .map(annotation -> new MethodAndAnnotation<>(method, annotation)));
    }

    protected Operation buildOperation(AsyncOperation asyncOperation, Method method, String channelName) {
        String description = this.resolver.resolveStringValue(asyncOperation.description());
        if (!StringUtils.hasText(description)) {
            description = "Auto-generated description";
        }

        String operationTitle = channelName + "_" + this.asyncAnnotationProvider.getOperationType().type;

        Map<String, OperationBinding> operationBinding =
                AsyncAnnotationUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        MessageObject message = buildMessage(asyncOperation, method);

        return Operation.builder()
                .channel(ChannelReference.fromChannel(channelName))
                .description(description)
                .title(operationTitle)
                .messages(List.of(MessageReference.toChannelMessage(channelName, message)))
                .bindings(opBinding)
                .build();
    }

    protected MessageObject buildMessage(AsyncOperation operationData, Method method) {
        Class<?> payloadType = operationData.payloadType() != Object.class
                ? operationData.payloadType()
                : payloadClassExtractor.extractFrom(method);

        String modelName = this.componentsService.registerSchema(
                payloadType, operationData.message().contentType());
        SchemaObject asyncHeaders = AsyncAnnotationUtil.getAsyncHeaders(operationData, resolver);
        String headerModelName = this.componentsService.registerSchema(asyncHeaders);
        var headers = MessageHeaders.of(MessageReference.toSchema(headerModelName));

        var schema = payloadType.getAnnotation(Schema.class);
        String description = schema != null ? schema.description() : null;

        Map<String, MessageBinding> messageBinding =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);

        var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        var builder = MessageObject.builder()
                .messageId(payloadType.getName())
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(description)
                .payload(messagePayload)
                .headers(headers)
                .bindings(messageBinding);

        // Retrieve the Message information obtained from the @AsyncMessage annotation. These values have higher
        // priority so if we find them, we need to override the default values.
        AsyncAnnotationUtil.processAsyncMessageAnnotation(builder, operationData.message(), this.resolver);

        MessageObject message = builder.build();
        this.componentsService.registerMessage(message);
        return message;
    }

    public interface AsyncAnnotationProvider<A> {
        Class<A> getAnnotation();

        AsyncOperation getAsyncOperation(A annotation);

        OperationAction getOperationType();
    }

    protected record MethodAndAnnotation<A>(Method method, A annotation) {}
}
