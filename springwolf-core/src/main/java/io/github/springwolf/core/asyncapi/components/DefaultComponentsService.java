// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link ComponentsService} interface. It provides services to resolve schemas or schema
 * names from given types. Additionally, this is a registry for all schemas and messages which are detected during the
 * scan process. Theses collected schemas and messages will provide to the content of the 'components' block
 * in the resulting AsyncApi object.
 */
@Slf4j
@AllArgsConstructor
public class DefaultComponentsService implements ComponentsService {

    private final SwaggerSchemaService schemaService;

    /**
     * maps a schema name (key) to a detected corresponding {@link ComponentSchema}.
     */
    private final Map<String, ComponentSchema> schemas = new HashMap<>();

    private final Map<String, Message> messages = new HashMap<>();

    /**
     * Provides a map of all registered schemas.
     *
     * @return the registered payload and header schemas
     */
    @Override
    public Map<String, ComponentSchema> getSchemas() {
        return schemas;
    }

    /**
     * Resolves a schema for the given type and contentType. Registers this schema and all
     * resolved schemas which a referenced by the root schema with this {@link ComponentsService}.
     *
     * @param type        Type to resolve a schema from
     * @param contentType Runtime ContentType of Schema
     * @return the root schema for the given type
     */
    @Override
    public ComponentSchema resolvePayloadSchema(Type type, String contentType) {
        SwaggerSchemaService.ExtractedSchemas payload = schemaService.resolveSchema(type, contentType);
        payload.referencedSchemas().forEach(schemas::putIfAbsent);
        return payload.rootSchema();
    }

    /**
     * registers the given schema with this {@link ComponentsService}.
     * <p>NOTE</p>
     * Use only with schemas with max. one level of properties. Providing {@link SchemaObject}s with deep
     * property hierarchy will result in a corrupted result.
     * <br/>
     * A typical usecase for this method is registering of header schemas, which have typically a simple structure.
     *
     * @param schemaWithoutRef the schema to register, typically a header schema
     * @return the title attribute of the given schema
     */
    @Override
    public String registerSchema(SchemaObject schemaWithoutRef) {
        log.debug("Registering schema for {}", schemaWithoutRef.getTitle());

        ComponentSchema processedSchema = schemaService.postProcessSchemaWithoutRef(schemaWithoutRef);
        this.schemas.putIfAbsent(schemaWithoutRef.getTitle(), processedSchema);

        return schemaWithoutRef.getTitle();
    }

    /**
     * Provides a map of all registered messages.
     *
     * @return the registered messages
     */
    @Override
    public Map<String, Message> getMessages() {
        return this.messages;
    }

    /**
     * registers the given message with this {@link ComponentsService}
     *
     * @param message the message to register
     * @return a {@link MessageReference} referencing the given message
     */
    @Override
    public MessageReference registerMessage(MessageObject message) {
        log.debug("Registering message for {}", message.getMessageId());

        messages.putIfAbsent(message.getMessageId(), message);

        return MessageReference.toComponentMessage(message);
    }

    /**
     * Provides a schema name for the given type. The result is either the full-qualified classname or
     * the simple classname of the given type - which depends on {@link SpringwolfConfigProperties#isUseFqn()}.
     *
     * @param type Type to generate a schema name from
     * @return the resulting schema name
     */
    @Override
    public String getSchemaName(Type type) {
        return schemaService.getNameFromType(type);
    }

    /**
     * Provides a simple schema name for the given type, which is the simple classname of the type.
     * @param type Type to generate a simple schema name from
     * @return the resulting schema name
     */
    @Override
    public String getSimpleSchemaName(Type type) {
        return schemaService.getSimpleNameFromType(type);
    }
}
