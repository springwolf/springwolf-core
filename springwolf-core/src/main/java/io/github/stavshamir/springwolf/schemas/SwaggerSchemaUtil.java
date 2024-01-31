// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import io.github.stavshamir.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SwaggerSchemaUtil {
    private SwaggerSchemaUtil() {}

    public static ComponentSchema mapSchemaOrRef(Schema schema) {
        if (schema.get$ref() != null) {
            return ComponentSchema.of(new MessageReference(schema.get$ref()));
        }
        return ComponentSchema.of(mapSchema(schema));
    }

    public static SchemaObject mapSchema(Schema value) {
        SchemaObject.SchemaObjectBuilder builder = SchemaObject.builder();

        //          TODO:              .discriminator(value.getDiscriminator())

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
            builder.additionalProperties(mapSchema((Schema<?>) additionalProperties));
        }

        builder.required(value.getRequired());

        List<Object> allOf = value.getAllOf();
        if (allOf != null) {
            builder.allOf(allOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchema((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        List<Object> oneOf = value.getOneOf();
        if (oneOf != null) {
            builder.oneOf(oneOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchema((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        List<Object> anyOf = value.getAnyOf();
        if (anyOf != null) {
            builder.anyOf(anyOf.stream()
                    .filter((el) -> el instanceof Schema<?>)
                    .map((Object schema) -> mapSchema((Schema<?>) schema))
                    .collect(Collectors.toList()));
        }

        builder.constValue(value.getConst());

        Schema not = value.getNot();
        if (not != null) {
            builder.not(mapSchema(not));
        }

        Schema items = value.getItems();
        if (items != null) {
            builder.items(mapSchema(items));
        }
        builder.uniqueItems(value.getUniqueItems());

        builder.minItems(value.getMinItems());
        builder.maxItems(value.getMaxItems());

        return builder.build();
    }
}
