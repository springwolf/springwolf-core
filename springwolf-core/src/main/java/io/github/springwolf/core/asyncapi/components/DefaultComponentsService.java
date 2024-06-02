// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class DefaultComponentsService implements ComponentsService {

    private final SwaggerSchemaService schemaService;
    private final SwaggerSchemaUtil swaggerSchemaUtil;

    private final Map<String, Schema> schemas = new HashMap<>();
    private final Map<String, Message> messages = new HashMap<>();

    @Override
    public Map<String, SchemaObject> getSchemas() {
        return schemas.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), swaggerSchemaUtil.mapSchema(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public SchemaObject resolveSchema(String schemaName) {
        if (schemas.containsKey(schemaName)) {
            return swaggerSchemaUtil.mapSchema(schemas.get(schemaName));
        }
        return null;
    }

    @Override
    public String registerSchema(SchemaObject headers) {
        log.debug("Registering schema for {}", headers.getTitle());

        ObjectSchema headerSchema = schemaService.extractSchema(headers);
        this.schemas.putIfAbsent(headers.getTitle(), headerSchema);

        return headers.getTitle();
    }

    @Override
    public String registerSchema(Class<?> type, String contentType) {
        log.debug("Registering schema for {}", type.getSimpleName());

        SwaggerSchemaService.ExtractedSchemas schemas = schemaService.extractSchema(type, contentType);
        schemas.schemas().forEach(this.schemas::putIfAbsent);

        return schemas.rootSchemaName();
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
}
