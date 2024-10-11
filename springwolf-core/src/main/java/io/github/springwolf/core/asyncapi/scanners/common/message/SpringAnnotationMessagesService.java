// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.message;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderSchemaObjectMerger;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static io.github.springwolf.core.asyncapi.scanners.common.MessageHelper.toMessagesMap;
import static io.github.springwolf.core.asyncapi.scanners.common.MessageHelper.toOperationsMessagesMap;
import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
public class SpringAnnotationMessagesService<ClassAnnotation extends Annotation> {

    private final BindingFactory<ClassAnnotation> bindingFactory;
    private final AsyncHeadersBuilder asyncHeadersBuilder;
    private final PayloadMethodService payloadMethodService;
    private final HeaderClassExtractor headerClassExtractor;
    private final ComponentsService componentsService;

    public enum MessageType {
        CHANNEL,
        OPERATION
    }

    public Map<String, MessageReference> buildMessages(
            ClassAnnotation classAnnotation, Set<Method> methods, MessageType messageType) {
        Set<MessageObject> messages = methods.stream()
                .map(method -> buildMessage(classAnnotation, method))
                .collect(toSet());

        if (messageType == MessageType.OPERATION) {
            String channelId = bindingFactory.getChannelName(classAnnotation);
            return toOperationsMessagesMap(channelId, messages);
        }
        return toMessagesMap(messages);
    }

    private MessageObject buildMessage(ClassAnnotation classAnnotation, Method method) {
        PayloadSchemaObject payloadSchema = payloadMethodService.extractSchema(method);

        SchemaObject headerSchema = asyncHeadersBuilder.buildHeaders(payloadSchema);
        SchemaObject headers = headerClassExtractor.extractHeader(method, payloadSchema);
        SchemaObject mergedHeaderSchema = HeaderSchemaObjectMerger.merge(headerSchema, headers);
        String headerSchemaName = componentsService.registerSchema(mergedHeaderSchema);

        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(classAnnotation, headerSchema);

        MessagePayload payload = MessagePayload.of(
                MultiFormatSchema.builder().schema(payloadSchema.payload()).build());

        MessageObject message = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.title())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerSchemaName)))
                .bindings(messageBinding)
                .build();

        this.componentsService.registerMessage(message);
        return message;
    }
}
