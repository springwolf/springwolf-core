// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import jakarta.annotation.Nullable;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * ComponentsService provides services to resolve schemas or schema names from given types.
 * Additionally, this is a registry for all schemas and messages which are detected during the
 * scan process. Theses collected schemas and messages will provide to the content of the 'components' block
 * in the resulting AsyncAPI object.
 */
public interface ComponentsService {

    /**
     * Provides a map of all registered schemas.
     *
     * @return the registered payload and header schemas
     */
    Map<String, ComponentSchema> getSchemas();

    /**
     * Resolves a schema for the given type and contentType. Registers this schema and all
     * resolved schemas which a referenced by the root schema with this {@link ComponentsService}.
     *
     * @param type        Type to resolve a schema from
     * @param contentType Runtime ContentType of Schema
     * @return a {@link SchemaReference} referencing the root schema, or null if no schema could be resolved.
     */
    @Nullable
    ComponentSchema resolvePayloadSchema(Type type, String contentType);

    /**
     * registers the given schema with this {@link ComponentsService}
     * @param schemaWithoutRef the schema to register, typically a header schema
     * @return the title attribute of the given schema
     */
    String registerSchema(SchemaObject schemaWithoutRef);

    /**
     * Provides a map of all registered messages.
     *
     * @return the registered messages
     */
    Map<String, Message> getMessages();

    /**
     * registers the given message with this {@link ComponentsService}
     *
     * @param message the message to register
     * @return a {@link MessageReference} referencing the given message
     */
    MessageReference registerMessage(MessageObject message);

    /**
     * Provides a schema name for the given type. The result is either the full-qualified classname or
     * the simple classname of the given type - which depends on {@link SpringwolfConfigProperties#isUseFqn()}.
     *
     * @param type Type to generate a schema name from
     * @return the resulting schema name
     */
    String getSchemaName(Type type);

    /**
     * Provides a simple schema name for the given type, which is the simple classname of the type.
     * @param type Type to generate a simple schema name from
     * @return the resulting schema name
     */
    String getSimpleSchemaName(Type type);
}
