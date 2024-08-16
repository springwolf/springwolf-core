// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SchemaObjectMerger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public abstract class MethodLevelAnnotationScanner<MethodAnnotation extends Annotation> {

    protected final BindingFactory<MethodAnnotation> bindingFactory;
    protected final AsyncHeadersBuilder asyncHeadersBuilder;
    protected final ComponentsService componentsService;

    protected MessageObject buildMessage(
            MethodAnnotation annotation, PayloadSchemaObject payloadSchema, SchemaObject headers) {
        SchemaObject headerSchema = asyncHeadersBuilder.buildHeaders(payloadSchema);
        SchemaObject mergedHeaderSchema = SchemaObjectMerger.merge(headerSchema, headers);
        String headerModelName = componentsService.registerSchema(mergedHeaderSchema);

        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(annotation, mergedHeaderSchema);

        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(payloadSchema.name()))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(payloadSchema.name())
                .name(payloadSchema.name())
                .title(payloadSchema.title())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.toSchema(headerModelName)))
                .bindings(messageBinding)
                .build();

        this.componentsService.registerMessage(message);
        return message;
    }
}
