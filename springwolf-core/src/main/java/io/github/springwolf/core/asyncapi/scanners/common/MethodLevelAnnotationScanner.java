// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
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

    protected MessageObject buildMessage(MethodAnnotation annotation, Class<?> payloadType, AsyncHeaders headers) {
        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(annotation);
        String modelName = componentsService.registerSchema(payloadType);

        MessageHeaders messageHeaders;
        String headerModelName;

        if (headers == null || headers.isEmpty()) {
            headerModelName = componentsService.registerSchema(asyncHeadersBuilder.buildHeaders(payloadType));
        } else {
            headerModelName = componentsService.registerSchema(headers);
        }
        messageHeaders = MessageHeaders.of(MessageReference.toSchema(headerModelName));

        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        MessageObject message = MessageObject.builder()
                .messageId(payloadType.getName())
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(payload)
                .headers(messageHeaders)
                .bindings(messageBinding)
                .build();

        this.componentsService.registerMessage(message);
        return message;
    }
}
