// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.postprocessor;

import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;

public class SwaggerSchemaPostProcessor implements SchemasPostProcessor {
    @Override
    public void process(Schema schema, Map<String, Schema> definitions) {
        removeAdditionalProperties(schema);
    }

    private void removeAdditionalProperties(Schema schema) {
        schema.setAdditionalProperties(null);

        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            properties.values().forEach((property) -> removeAdditionalProperties(property));
        }
    }
}
