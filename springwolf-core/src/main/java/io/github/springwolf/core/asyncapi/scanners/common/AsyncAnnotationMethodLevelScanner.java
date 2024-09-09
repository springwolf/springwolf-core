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
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public abstract class AsyncAnnotationMethodLevelScanner<A extends Annotation> {

    protected final AsyncAnnotationProvider<A> asyncAnnotationProvider;
    protected final PayloadAsyncOperationService payloadAsyncOperationService;
    protected final ComponentsService componentsService;
    protected final List<OperationBindingProcessor> operationBindingProcessors;
    protected final List<MessageBindingProcessor> messageBindingProcessors;
    protected final StringValueResolver resolver;

    protected Operation buildOperation(AsyncOperation asyncOperation, Method method, String channelId) {
        String description = this.resolver.resolveStringValue(asyncOperation.description());
        if (StringUtils.isBlank(description)) {
            description = "Auto-generated description";
        } else {
            description = TextUtils.trimIndent(description);
        }

        String operationTitle =
                StringUtils.joinWith("_", channelId, this.asyncAnnotationProvider.getOperationType().type);

        Map<String, OperationBinding> operationBinding =
                AsyncAnnotationUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        MessageObject message = buildMessage(asyncOperation, method);

        return Operation.builder()
                .channel(ChannelReference.fromChannel(channelId))
                .description(description)
                .title(operationTitle)
                .messages(List.of(MessageReference.toChannelMessage(channelId, message)))
                .bindings(opBinding)
                .build();
    }

    protected MessageObject buildMessage(AsyncOperation operationData, Method method) {
        PayloadSchemaObject payloadSchema = payloadAsyncOperationService.extractSchema(operationData, method);

        SchemaObject headerSchema = AsyncAnnotationUtil.getAsyncHeaders(operationData, resolver);
        String headerSchemaName = this.componentsService.registerSchema(headerSchema);

        Map<String, MessageBinding> messageBinding =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);

        var messagePayload = MessagePayload.of(
                MultiFormatSchema.builder().schema(payloadSchema.payload()).build());

        String description = operationData.message().description();
        if (StringUtils.isBlank(description) && payloadSchema.payload() instanceof SchemaObject) {
            String payloadDescription = ((SchemaObject) payloadSchema.payload()).getDescription();
            if (StringUtils.isNotBlank(payloadDescription)) {
                description = payloadDescription;
            }
        }
        if (StringUtils.isNotBlank(description)) {
            description = this.resolver.resolveStringValue(description);
            description = TextUtils.trimIndent(description);
        } else {
            description = null;
        }

        var builder = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.title())
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
}
