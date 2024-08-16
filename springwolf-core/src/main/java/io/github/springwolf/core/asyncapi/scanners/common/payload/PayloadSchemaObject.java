// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import jakarta.annotation.Nullable;

/**
 * Encapsulates the resolved name for the contained schema.
 * @param name The fully qualified name or the simple name of the schema.
 * @param schema The SchemaObject.
 */
public record PayloadSchemaObject(String name, @Nullable SchemaObject schema) {
    public String title() {
        return schema != null ? schema.getTitle() : name();
    }
}
