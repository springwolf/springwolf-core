// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.postprocessors;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.util.StringUtils;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Removes internal avro fields and classes from the schema.
 * <br/>
 * For now, this class is located in springwolf-core as it provides value for the cloud-stream and kafka plugin.
 * To avoid to many (one-class) add-ons, this class was not moved to yet another artifact - as also no new dependencies are required.
 * This may change in the future.
 */
public class AvroSchemaPostProcessor implements SchemasPostProcessor {
    private static final String SCHEMA_AVRO_PREFIX = "org.apache.avro.";
    private static final String SCHEMA_PROPERTY = "schema";
    private static final String SPECIFIC_DATA_PROPERTY = "specificData";
    private static final String SCHEMA_REF = "org.apache.avro.Schema";
    private static final String SPECIFIC_DAT_REF = "org.apache.avro.specific.SpecificData";

    @Override
    public void process(Schema<?> schema, Map<String, Schema<?>> definitions, String contentType) {
        removeSchemas(definitions);

        Deque<Schema<?>> queue = new LinkedList<>(List.of(schema));
        Set<Schema<?>> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Schema<?> currentSchema = queue.pop();
            if (visited.contains(currentSchema)) {
                continue;
            }
            visited.add(currentSchema);

            processRefSchema(currentSchema, queue, definitions);
            processProperties(currentSchema, queue);
        }
    }

    private void processRefSchema(Schema<?> schema, Deque<Schema<?>> queue, Map<String, Schema<?>> definitions) {
        if (schema.get$ref() != null) {
            String schemaName = ReferenceUtil.getLastSegment(schema.get$ref());
            Schema<?> refedSchema = definitions.get(schemaName);
            if (refedSchema != null) {
                queue.add(refedSchema);
            }
        }
    }

    private void processProperties(Schema<?> schema, Deque<Schema<?>> queue) {
        Map<?, ?> properties = schema.getProperties();
        if (properties != null) {
            Object schemaProperty = properties.get(SCHEMA_PROPERTY);
            Object specificDataProperty = properties.get(SPECIFIC_DATA_PROPERTY);
            if (schemaProperty instanceof Schema<?> schemaPropertySchema
                    && specificDataProperty instanceof Schema<?> specificDataPropertySchema) {
                if (StringUtils.endsWithIgnoreCase(schemaPropertySchema.get$ref(), SCHEMA_REF)
                        && StringUtils.endsWithIgnoreCase(specificDataPropertySchema.get$ref(), SPECIFIC_DAT_REF)) {
                    properties.remove(SCHEMA_PROPERTY);
                    properties.remove(SPECIFIC_DATA_PROPERTY);
                }
            }

            properties.forEach((key, value) -> {
                if (value instanceof Schema<?> propertySchema) {
                    queue.add(propertySchema);
                } else {
                    throw new IllegalStateException("Expected a Swagger Schema for property " + key);
                }
            });
        }
    }

    private void removeSchemas(Map<String, Schema<?>> definitions) {
        definitions.entrySet().removeIf(entry -> StringUtils.startsWithIgnoreCase(entry.getKey(), SCHEMA_AVRO_PREFIX));
    }
}
