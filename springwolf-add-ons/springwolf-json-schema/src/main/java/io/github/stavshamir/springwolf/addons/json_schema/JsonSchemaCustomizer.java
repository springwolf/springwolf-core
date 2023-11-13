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
        for (Map.Entry<String, Schema> entry : schemas.entrySet()) {
            Schema schema = entry.getValue();
            if (schema.getExtensions() == null) {
                schema.setExtensions(new HashMap<>());
            }

            try {
                log.debug("Generate json-schema for %s".formatted(entry.getKey()));

                Object jsonSchema = jsonSchemaGenerator.fromSchema(schema, schemas);
                schema.getExtensions().putIfAbsent(EXTENSION_JSON_SCHEMA,  jsonSchema);
            } catch (Exception ex) {
                log.debug("Unable to create json-schema for %s".formatted(schema.getName()), ex);
            }
        }
    }
}
