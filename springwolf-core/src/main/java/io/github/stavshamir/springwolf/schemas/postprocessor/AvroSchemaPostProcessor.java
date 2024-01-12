// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.Schema;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Removes internal avro fields and classes from the schema.
 * <br/>
 * For now, this class is located in springwolf-core as it provides value for the cloud-stream and kafka plugin.
 * To avoid to many (one-class) add-ons, this class was not moved to yet another artifact - as also no new dependencies are required.
 * This may change in the future.
 */
public class AvroSchemaPostProcessor implements SchemasPostProcessor {
    private static final String SCHEMA_PROPERTY = "schema";
    private static final String SPECIFIC_DATA_PROPERTY = "specificData";
    private static final String SCHEMA_REF = "org.apache.avro.Schema";
    private static final String SPECIFIC_DAT_REF = "org.apache.avro.specific.SpecificData";

    @Override
    public void process(Schema schema, Map<String, Schema> definitions) {
        removeAvroSchemas(definitions);
        removeAvroProperties(schema);
    }

    private void removeAvroProperties(Schema schema) {
        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            Schema schemaPropertySchema = properties.getOrDefault(SCHEMA_PROPERTY, null);
            Schema specificDataPropertySchema = properties.getOrDefault(SPECIFIC_DATA_PROPERTY, null);
            if (schemaPropertySchema != null && specificDataPropertySchema != null) {
                if (StringUtils.endsWithIgnoreCase(schemaPropertySchema.get$ref(), SCHEMA_REF)
                        && StringUtils.endsWithIgnoreCase(specificDataPropertySchema.get$ref(), SPECIFIC_DAT_REF)) {
                    properties.remove(SCHEMA_PROPERTY);
                    properties.remove(SPECIFIC_DATA_PROPERTY);
                }
            }
        }
    }

    private void removeAvroSchemas(Map<String, Schema> definitions) {
        definitions.entrySet().removeIf(entry -> StringUtils.startsWithIgnoreCase(entry.getKey(), "org.apache.avro"));
    }
}
