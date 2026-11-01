// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.message;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.messages.MessagesService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderSchemaObjectMerger;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class SpringAnnotationMessageService<MethodAnnotation extends Annotation> {

    private final BindingFactory<MethodAnnotation> bindingFactory;
    private final AsyncHeadersBuilder asyncHeadersBuilder;
    private final MessagesService messagesService;
    private final SchemasService schemasService;

    public MessageObject buildMessage(
            MethodAnnotation annotation, PayloadSchemaObject payloadSchema, SchemaObject headers) {
        SchemaObject headerSchema = asyncHeadersBuilder.buildHeaders(payloadSchema);
        SchemaObject mergedHeaderSchema = HeaderSchemaObjectMerger.merge(headerSchema, headers);
        String headerModelName = schemasService.registerSchema(mergedHeaderSchema);

        BindingContext<MethodAnnotation> bindingContext = BindingContext.of(annotation);
        Map<String, MessageBinding> messageBinding =
                bindingFactory.buildMessageBinding(bindingContext, mergedHeaderSchema);

        MessagePayload payload = MessagePayload.of(
                MultiFormatSchema.builder().schema(payloadSchema.payload()).build());

        MessageObject message = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.title())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(SchemaReference.toSchema(headerModelName)))
                .bindings(messageBinding)
                .build();

        this.messagesService.registerMessage(message);
        return message;
    }
}
