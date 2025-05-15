// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import jakarta.annotation.Nullable;

/**
 * Encapsulates the resolved name for the contained schema.
 *
 * @param name The fully qualified name or the simple name of the schema.
 * @param simpleSchemaName
 * @param schema The SchemaObject.
 */
public record PayloadSchemaObject(String name, String simpleSchemaName, @Nullable ComponentSchema schema) {
    public String title() {
        return (simpleSchemaName() != null) ? simpleSchemaName() : name();
    }

    public Object payload() {
        if (schema() != null) {
            if (schema().getSchema() != null) {
                return schema().getSchema();
            }
            if (schema().getReference() != null) {
                return schema().getReference();
            }
            if (schema().getMultiFormatSchema() != null) {
                return schema().getMultiFormatSchema();
            }
        }
        return SchemaReference.fromSchema(name());
    }
}
