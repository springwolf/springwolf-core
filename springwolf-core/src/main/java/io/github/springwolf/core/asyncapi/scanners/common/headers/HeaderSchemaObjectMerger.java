// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HeaderSchemaObjectMerger {

    public static SchemaObject merge(SchemaObject initial, SchemaObject... schemas) {

        int additionalProperties = Arrays.stream(schemas)
                .filter(schemaObject -> schemaObject != null && schemaObject.getProperties() != null)
                .mapToInt(schema -> schema.getProperties().size())
                .sum();
        if (additionalProperties == 0) {
            return initial;
        }

        SchemaObject.SchemaObjectBuilder headerSchemaBuilder =
                SchemaObject.builder().type(SchemaType.OBJECT);

        String title = initial.getTitle();
        String description = initial.getDescription();
        Map<String, Object> headerProperties = new HashMap<>(initial.getProperties());

        for (SchemaObject schema : schemas) {
            if (schema == null) {
                continue;
            }

            if (StringUtils.isBlank(description)) {
                description = schema.getDescription();
            }

            schema.getProperties().forEach(headerProperties::putIfAbsent);
        }

        return headerSchemaBuilder
                .title(generateTitle(initial, headerProperties))
                .description(description)
                .properties(headerProperties)
                .build();
    }

    public static String generateHeaderSchemaName(Object object) {
        return generateHeaderSchemaName("Headers", object);
    }

    private static String generateHeaderSchemaName(String prefix, Object object) {
        return prefix + "-" + Math.abs(object.hashCode());
    }

    private static String generateTitle(SchemaObject initial, Map<String, Object> headerProperties) {
        if (StringUtils.isBlank(initial.getTitle())) {
            return generateHeaderSchemaName(headerProperties);
        } else {
            return generateHeaderSchemaName(initial.getTitle(), headerProperties.hashCode());
        }
    }
}
