// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.addons.json_schema;

import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.AsyncApiCustomizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * adds a custom 'x-json-schema' field to every schema in the 'components' block, which contains the schema of the
 * current type formatted as json-schema.
 */
@RequiredArgsConstructor
@Slf4j
public class JsonSchemaCustomizer implements AsyncApiCustomizer {
    private static final String EXTENSION_JSON_SCHEMA = "x-json-schema";

    private final JsonSchemaGenerator jsonSchemaGenerator;

    @Override
    public void customize(AsyncAPI asyncAPI) {
        Map<String, ComponentSchema> componentSchemas = asyncAPI.getComponents().getSchemas();
        for (Map.Entry<String, ComponentSchema> entry : componentSchemas.entrySet()) {
            ComponentSchema componentSchema = entry.getValue();

            SchemaObject schema = componentSchema.getSchema();

            if (schema != null) {
                if (schema.getExtensionFields() == null) {
                    schema.setExtensionFields(new HashMap<>());
                }

                try {
                    log.trace("Generate json-schema for {}", entry.getKey());

                    Object jsonSchema = jsonSchemaGenerator.fromSchema(componentSchema, componentSchemas);
                    if (jsonSchema != null) {
                        schema.getExtensionFields().putIfAbsent(EXTENSION_JSON_SCHEMA, jsonSchema);
                    }
                } catch (Exception ex) {
                    log.info("Unable to create json-schema for {}", entry.getKey(), ex);
                }
            }
        }
    }
}
