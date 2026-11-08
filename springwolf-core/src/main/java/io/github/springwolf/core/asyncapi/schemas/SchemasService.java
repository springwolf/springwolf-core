// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.Map;

public interface SchemasService {
    Map<String, ComponentSchema> getSchemas();

    @Nullable
    ComponentSchema registerType(Type type, String contentType);

    String registerSchema(SchemaObject schemaWithoutRef);

    String getSchemaName(Type type);

    String getSimpleSchemaName(Type type);
}
