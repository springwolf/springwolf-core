// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import jakarta.annotation.Nullable;

/**
 * Encapsulates the resolved name for the contained schema.
 * @param name The fully qualified name or the simple name of the schema.
 * @param schema The SchemaObject.
 * @param schemaPayload The schema-payload to be inserted in the message, when not null this schema will override the payload of the message.
 */
public record PayloadSchemaObject(String name, @Nullable SchemaObject schema, @Nullable SchemaObject schemaPayload) {
    public String title() {
        return schema != null ? schema.getTitle() : name();
    }

    public Object payload() {
        if (schemaPayload() != null) {
            return schemaPayload();
        }
        return MessageReference.toSchema(name());
    }
}
