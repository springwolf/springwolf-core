// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.schema.SwaggerSchemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class DefaultSchemasService implements SchemasService {
    private final SwaggerSchemaService schemaService;
    private final Map<String, ComponentSchema> schemas = new HashMap<>();

    @Override
    public Map<String, ComponentSchema> getSchemas() {
        return schemas;
    }

    @Override
    public ComponentSchema registerType(Type type, String contentType) {
        SwaggerSchemaService.ExtractedSchemas payload = schemaService.resolveSchema(type, contentType);
        payload.referencedSchemas().forEach(schemas::putIfAbsent);
        return payload.rootSchema();
    }

    @Override
    public String registerSchema(SchemaObject schemaWithoutRef) {
        log.trace("Registering schema for {}", schemaWithoutRef.getTitle());
        ComponentSchema processedSchema = schemaService.postProcessSchemaWithoutRef(schemaWithoutRef);
        schemas.putIfAbsent(schemaWithoutRef.getTitle(), processedSchema);
        return schemaWithoutRef.getTitle();
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
