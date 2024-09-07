// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class DefaultComponentsService implements ComponentsService {

    private final SwaggerSchemaService schemaService;

    private final Map<String, SchemaObject> schemas = new HashMap<>();
    private final Map<String, Message> messages = new HashMap<>();

    @Override
    public Map<String, SchemaObject> getSchemas() {
        return schemas;
    }

    @Override
    public ComponentSchema resolvePayloadSchema(Type type, String contentType) {
        SwaggerSchemaService.Payload payload = schemaService.resolvePayloadSchema(type, contentType);
        payload.referencedSchemas().forEach(this.schemas::putIfAbsent);
        return payload.payloadSchema();
    }

    @Override
    public String registerSchema(SchemaObject headers) {
        log.debug("Registering schema for {}", headers.getTitle());

        SchemaObject headerSchema = schemaService.extractSchema(headers);
        this.schemas.putIfAbsent(headers.getTitle(), headerSchema);

        return headers.getTitle();
    }

    @Override
    public Map<String, Message> getMessages() {
        return this.messages;
    }

    @Override
    public MessageReference registerMessage(MessageObject message) {
        log.debug("Registering message for {}", message.getMessageId());

        messages.putIfAbsent(message.getMessageId(), message);

        return MessageReference.toComponentMessage(message);
    }

    @Override
    public String getSchemaName(Type type) {
        return schemaService.getNameFromType(type);
    }

    @Override
    public String getSimpleSchemaName(Type type) {
        return schemaService.getSimpleNameFromType(type);
    }
}
