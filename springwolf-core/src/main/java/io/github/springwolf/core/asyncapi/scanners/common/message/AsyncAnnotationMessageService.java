// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.message;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AsyncAnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.utils.TextUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationMessageService {

    private final PayloadAsyncOperationService payloadAsyncOperationService;
    private final ComponentsService componentsService;
    private final List<MessageBindingProcessor> messageBindingProcessors;
    private final StringValueResolver stringValueResolver;

    public MessageObject buildMessage(AsyncOperation operationData, Method method) {
        PayloadSchemaObject payloadSchema = payloadAsyncOperationService.extractSchema(operationData, method);

        SchemaObject headerSchema = AsyncAnnotationUtil.getAsyncHeaders(operationData, stringValueResolver);
        String headerSchemaName = this.componentsService.registerSchema(headerSchema);

        Map<String, MessageBinding> messageBinding =
                AsyncAnnotationUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);

        MultiFormatSchema multiFormatSchema = MultiFormatSchema.of(payloadSchema.payload());
        MessagePayload messagePayload = MessagePayload.of(multiFormatSchema);

        var builder = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.title())
                .description(getDescription(operationData, payloadSchema))
                .payload(messagePayload)
                .headers(MessageHeaders.of(SchemaReference.toSchema(headerSchemaName)))
                .bindings(messageBinding);
        // Retrieve the Message information obtained from the @AsyncMessage annotation. These values have higher
        // priority so if we find them, we need to override the default values.
        AsyncAnnotationUtil.processAsyncMessageAnnotation(builder, operationData.message(), this.stringValueResolver);
        MessageObject message = builder.build();

        this.componentsService.registerMessage(message);
        return message;
    }

    private String getDescription(AsyncOperation operationData, PayloadSchemaObject payloadSchema) {
        String description = operationData.message().description();
        if (StringUtils.isBlank(description) && payloadSchema.payload() instanceof SchemaObject) {
            String payloadDescription = ((SchemaObject) payloadSchema.payload()).getDescription();
            if (StringUtils.isNotBlank(payloadDescription)) {
                description = payloadDescription;
            }
        }
        if (StringUtils.isNotBlank(description)) {
            description = this.stringValueResolver.resolveStringValue(description);
            description = TextUtils.trimIndent(description);
        } else {
            description = null;
        }
        return description;
    }
}
