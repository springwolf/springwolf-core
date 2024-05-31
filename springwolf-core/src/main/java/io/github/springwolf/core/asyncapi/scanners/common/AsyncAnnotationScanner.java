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
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.ReflectionUtils;
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
    protected final PayloadService payloadService;
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

        return Arrays.stream(ReflectionUtils.getAllDeclaredMethods(type))
                .filter(AnnotationScannerUtil::isMethodInSourceCode)
                .filter(method -> AnnotationScannerUtil.findAnnotation(annotationClass, method) != null)
                .peek(method -> log.debug("Mapping method \"{}\" to channels", method.getName()))
                .flatMap(method -> AnnotationScannerUtil.findAnnotations(annotationClass, method).stream()
                        .map(annotation -> new MethodAndAnnotation<>(method, annotation)));
    }

    protected Operation buildOperation(AsyncOperation asyncOperation, Method method, String channelName) {
        String description = this.resolver.resolveStringValue(asyncOperation.description());
        if (!StringUtils.hasText(description)) {
            description = "Auto-generated description";
        } else {
            description = TextUtils.trimIndent(description);
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
        NamedSchemaObject payloadSchema = payloadService.extractSchema(operationData, method);

        SchemaObject headerSchema = AsyncAnnotationUtil.getAsyncHeaders(operationData, resolver);
        String headerSchemaName = this.componentsService.registerSchema(headerSchema);

        Map<String, MessageBinding> messageBinding =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);

        var messagePayload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(payloadSchema.name()))
                .build());

        String description = operationData.message().description();
        if (!StringUtils.hasText(description)) {
            description = payloadSchema.schema().getDescription();
        }
        if (StringUtils.hasText(description)) {
            description = this.resolver.resolveStringValue(description);
            description = TextUtils.trimIndent(description);
        }

        var builder = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.schema().getTitle())
                .description(description)
                .payload(messagePayload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerSchemaName)))
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
