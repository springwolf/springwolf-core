// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.json_schema;


import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
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
        Map<String, SchemaObject> schemas = asyncAPI.getComponents().getSchemas();
        for (Map.Entry<String, SchemaObject> entry : schemas.entrySet()) {
            SchemaObject schema = entry.getValue();

            if (schema != null) {
                if (schema.getExtensionFields() == null) {
                    schema.setExtensionFields(new HashMap<>());
                }

                try {
                    log.debug("Generate json-schema for %s".formatted(entry.getKey()));

                    Object jsonSchema = jsonSchemaGenerator.fromSchema(schema, schemas);
                    schema.getExtensionFields().putIfAbsent(EXTENSION_JSON_SCHEMA, jsonSchema);
                } catch (Exception ex) {
                    log.warn("Unable to create json-schema for %s".formatted(entry.getKey()), ex);
                }
            }
        }
    }
}
