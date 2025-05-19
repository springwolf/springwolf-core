// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SwaggerSchemaUtil {

    public Map<String, SchemaObject> mapSchemasMap(Map<String, Schema> schemaMap) {
        return schemaMap.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), mapSchema(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public ComponentSchema mapSchemaOrRef(Schema schema) {
        if (schema.get$ref() != null) {
            return ComponentSchema.of(new SchemaReference(schema.get$ref()));
        }
        return ComponentSchema.of(mapSchema(schema));
    }

    public SchemaObject mapSchema(Schema value) {
        SchemaObject.SchemaObjectBuilder builder = SchemaObject.builder();

        io.swagger.v3.oas.models.ExternalDocumentation externalDocs = value.getExternalDocs();
        if (externalDocs != null) {
            ExternalDocumentation externalDocumentation = ExternalDocumentation.builder()
                    .description(externalDocs.getDescription())
                    .url(externalDocs.getUrl())
                    .build();
            builder.externalDocs(externalDocumentation);
        }

        builder.deprecated(value.getDeprecated());

        builder.title(value.getTitle());

        builder.type(value.getType());

        Map<String, Schema> properties = value.getProperties();
        if (properties != null) {
            Map<String, Object> propertiesMapped = properties.entrySet().stream()
                    .map(entry -> Map.entry(entry.getKey(), mapSchemaOrRef(entry.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            builder.properties(propertiesMapped);
        }

        builder.description(value.getDescription());

        builder.format(value.getFormat());
        builder.pattern(value.getPattern());

        if (value.getExclusiveMinimum() != null && value.getExclusiveMinimum()) {
            builder.exclusiveMinimum(value.getMinimum());
        } else if (value.getExclusiveMinimumValue() != null) {
            builder.exclusiveMinimum(value.getExclusiveMinimumValue());
        } else {
            builder.minimum(value.getMinimum());
        }
        if (value.getExclusiveMaximum() != null && value.getExclusiveMaximum()) {
            builder.exclusiveMaximum(value.getMaximum());
        } else if (value.getExclusiveMaximumValue() != null) {
            builder.exclusiveMaximum(value.getExclusiveMaximumValue());
        } else {
            builder.maximum(value.getMaximum());
        }

        builder.multipleOf(value.getMultipleOf());

        builder.minLength(value.getMinLength());
        builder.maxLength(value.getMaxLength());

        List<Object> anEnum = value.getEnum();
        if (anEnum != null) {
            builder.enumValues(anEnum.stream().map(Object::toString).toList());
        }

        Object example = value.getExample();
        if (example != null) {
            builder.examples(List.of(example));
        }
        List<Object> examples = value.getExamples();
        if (examples != null && !examples.isEmpty()) {
            builder.examples(examples);
        }

        Object additionalProperties = value.getAdditionalProperties();
        if (additionalProperties instanceof Schema) {
            builder.additionalProperties(mapSchemaOrRef((Schema<?>) additionalProperties));
        }

        builder.required(value.getRequired());

        if (value.getDiscriminator() != null) {
            builder.discriminator(value.getDiscriminator().getPropertyName());
        }

        List<Object> allOf = value.getAllOf();
        if (allOf != null) {
            builder.allOf(allOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchemaOrRef((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        List<Object> oneOf = value.getOneOf();
        if (oneOf != null) {
            builder.oneOf(oneOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchemaOrRef((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        List<Object> anyOf = value.getAnyOf();
        if (anyOf != null) {
            builder.anyOf(anyOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchemaOrRef((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        builder.constValue(value.getConst());

        Schema not = value.getNot();
        if (not != null) {
            builder.not(mapSchemaOrRef(not));
        }

        Schema items = value.getItems();
        if (items != null && "array".equals(value.getType())) {
            builder.items(mapSchemaOrRef(items));
        }
        builder.uniqueItems(value.getUniqueItems());

        builder.minItems(value.getMinItems());
        builder.maxItems(value.getMaxItems());

        return builder.build();
    }

    public Schema mapToSwagger(SchemaObject asyncApiSchema) {
        Schema swaggerSchema = new Schema();
        swaggerSchema.setType(asyncApiSchema.getType());
        swaggerSchema.setDescription(asyncApiSchema.getDescription());
        swaggerSchema.setExamples(asyncApiSchema.getExamples());
        swaggerSchema.setEnum(asyncApiSchema.getEnumValues());

        return swaggerSchema;
    }
}
