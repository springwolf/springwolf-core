// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;
import java.util.Set;

public class SwaggerSchemaPostProcessor implements SchemasPostProcessor {
    @Override
    public void process(Schema schema, Map<String, Schema> definitions) {
        fixOpenApiSchemaDiscrepancies(schema);
        removeAdditionalProperties(schema);

        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            properties.values().forEach((property) -> process(property, definitions));
        }
    }

    private void fixOpenApiSchemaDiscrepancies(Schema schema) {
        if (schema.getType() != null) {
            schema.setTypes(Set.of(schema.getType()));
        }

        if (schema.getExclusiveMinimum() != null) {
            schema.setExclusiveMinimumValue(schema.getMinimum());
            schema.setExclusiveMinimum(null);
        }
        if (schema.getExclusiveMaximum() != null) {
            schema.setExclusiveMaximumValue(schema.getMaximum());
            schema.setExclusiveMaximum(null);
        }
    }

    private void removeAdditionalProperties(Schema schema) {
        schema.setAdditionalProperties(null);
    }
}
