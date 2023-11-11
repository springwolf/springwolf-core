// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.json_schema;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiCustomizer;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class JsonSchemaCustomizer implements AsyncApiCustomizer {
    private static final String EXTENSION_JSON_SCHEMA = "x-json-schema";

    private final JsonSchemaGenerator jsonSchemaGenerator;

    @Override
    public void customize(AsyncAPI asyncAPI) {
        Map<String, Schema> schemas = asyncAPI.getComponents().getSchemas();
        for (Schema<?> schema : schemas.values()) {
            if (schema.getExtensions() == null) {
                schema.setExtensions(new HashMap<>());
            }

            try {
                Object jsonSchema = jsonSchemaGenerator.fromSchema(schema, schemas);
                schema.getExtensions().computeIfAbsent(EXTENSION_JSON_SCHEMA, (key) -> jsonSchema);
            } catch (Exception ex) {
                log.debug("Unable to create json schema for %s".formatted(schema.getName()), ex);
            }
        }
    }
}
