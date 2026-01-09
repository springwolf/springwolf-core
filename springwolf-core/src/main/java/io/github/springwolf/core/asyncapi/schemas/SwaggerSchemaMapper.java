// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.configuration.properties.PayloadSchemaFormat;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utils class providing services to map between Swagger schemas and AsyncApi schemas.
 */
@RequiredArgsConstructor
public class SwaggerSchemaMapper {

    private final SpringwolfConfigProperties springwolfConfigProperties;

    public Map<String, ComponentSchema> mapSchemasMap(Map<String, Schema> schemaMap) {
        return schemaMap.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(), mapSchema(entry.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * creates a {@link ComponentSchema} from the given Swagger schema. If the given Swagger schema represents a
     * Reference, the resulting {@link ComponentSchema} will contain a corresponding {@link SchemaReference} to the
     * referenced location. Otherwise, the given Swagger schema is converted to an AsnycApi {@link SchemaObject} and
     * put into the resulting {@link ComponentSchema}
     *
     * @param swaggerSchema the Swagger schema to convert
     * @return ComponentSchema with either a {@link SchemaReference} or a {@link SchemaObject}.
     */
    public ComponentSchema mapSchemaOrRef(Schema swaggerSchema) {
        if (swaggerSchema.get$ref() != null) {
            return ComponentSchema.of(new SchemaReference(swaggerSchema.get$ref()));
        }
        return mapSchema(swaggerSchema);
    }

    /**
     * Converts the given Swagger schema to a {ComponentSchema} with a given schemaFormat.
     * If schemaFormat is an openapi format, the given swaggerSchema is simply wrapped to an {@link ComponentSchema}.
     * <p>
     * Properties are mapped recursively except
     * as long as the child schemas are 'real' schemas and not schema references. So this method performs a deep conversion
     * of the entire Swagger schema.
     *
     * @param
     * @param swaggerSchema the given Swagger schema instance
     * @return the resulting AsnycApi SchemaObject
     */
    public ComponentSchema mapSchema(Schema swaggerSchema) {
        PayloadSchemaFormat payloadSchemaFormat =
                springwolfConfigProperties.getDocket().getPayloadSchemaFormat();
        return switch (payloadSchemaFormat) {
            case OPENAPI_V3, OPENAPI_V3_1 ->
                ComponentSchema.of(new MultiFormatSchema(payloadSchemaFormat.getSchemaFormat().value, swaggerSchema));
            case ASYNCAPI_V3 -> ComponentSchema.of(mapSwaggerSchemaToAsyncApiSchema(swaggerSchema));
        };
    }

    /**
     * transforms the given Swagger schema to an AsyncApi schema.
     *
     * @param swaggerSchema
     * @return
     */
    private SchemaObject mapSwaggerSchemaToAsyncApiSchema(Schema<?> swaggerSchema) {

        SchemaObject.SchemaObjectBuilder builder = SchemaObject.builder();

        io.swagger.v3.oas.models.ExternalDocumentation externalDocs = swaggerSchema.getExternalDocs();
        if (externalDocs != null) {
            ExternalDocumentation externalDocumentation = ExternalDocumentation.builder()
                    .description(externalDocs.getDescription())
                    .url(externalDocs.getUrl())
                    .build();
            builder.externalDocs(externalDocumentation);
        }

        builder.deprecated(swaggerSchema.getDeprecated());

        builder.title(swaggerSchema.getTitle());

        boolean isNullable = Boolean.TRUE.equals(swaggerSchema.getNullable());
        assignType(swaggerSchema, builder, isNullable);

        Map<String, Schema> properties = swaggerSchema.getProperties();
        if (properties != null) {
            Map<String, Object> propertiesMapped = properties.entrySet().stream()
                    .map(entry -> Map.entry(entry.getKey(), mapSchemaOrRef(entry.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

            builder.properties(propertiesMapped);
        }

        builder.description(swaggerSchema.getDescription());

        builder.format(swaggerSchema.getFormat());
        builder.pattern(swaggerSchema.getPattern());

        if (swaggerSchema.getExclusiveMinimum() != null && swaggerSchema.getExclusiveMinimum()) {
            builder.exclusiveMinimum(swaggerSchema.getMinimum());
        } else if (swaggerSchema.getExclusiveMinimumValue() != null) {
            builder.exclusiveMinimum(swaggerSchema.getExclusiveMinimumValue());
        } else {
            builder.minimum(swaggerSchema.getMinimum());
        }
        if (swaggerSchema.getExclusiveMaximum() != null && swaggerSchema.getExclusiveMaximum()) {
            builder.exclusiveMaximum(swaggerSchema.getMaximum());
        } else if (swaggerSchema.getExclusiveMaximumValue() != null) {
            builder.exclusiveMaximum(swaggerSchema.getExclusiveMaximumValue());
        } else {
            builder.maximum(swaggerSchema.getMaximum());
        }

        builder.multipleOf(swaggerSchema.getMultipleOf());

        builder.minLength(swaggerSchema.getMinLength());
        builder.maxLength(swaggerSchema.getMaxLength());

        List<?> anEnum = swaggerSchema.getEnum();
        if (anEnum != null) {
            List<String> enumStringValues =
                    anEnum.stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));
            if (isNullable) {
                enumStringValues.add(null);
            }
            builder.enumValues(enumStringValues);
        }

        Object example = swaggerSchema.getExample();
        if (example != null) {
            builder.examples(List.of(example));
        }
        List examples = swaggerSchema.getExamples();
        if (examples != null && !examples.isEmpty()) {
            builder.examples(examples);
        }

        Object additionalProperties = swaggerSchema.getAdditionalProperties();
        if (additionalProperties instanceof Schema<?> schema) {
            builder.additionalProperties(mapSchemaOrRef(schema));
        }

        builder.required(swaggerSchema.getRequired());

        if (swaggerSchema.getDiscriminator() != null) {
            builder.discriminator(swaggerSchema.getDiscriminator().getPropertyName());
        }

        List<Schema> allOf = swaggerSchema.getAllOf();
        if (allOf != null) {
            builder.allOf(allOf.stream().map(this::mapSchemaOrRef).collect(Collectors.toList()));
        }

        List<Schema> oneOf = swaggerSchema.getOneOf();
        if (oneOf != null) {
            builder.oneOf(oneOf.stream().map(this::mapSchemaOrRef).collect(Collectors.toList()));
        }

        List<Schema> anyOf = swaggerSchema.getAnyOf();
        if (anyOf != null) {
            builder.anyOf(anyOf.stream().map(this::mapSchemaOrRef).collect(Collectors.toList()));
        }

        builder.constValue(swaggerSchema.getConst());

        Schema not = swaggerSchema.getNot();
        if (not != null) {
            builder.not(mapSchemaOrRef(not));
        }

        Schema items = swaggerSchema.getItems();
        if (items != null && "array".equals(swaggerSchema.getType())) {
            builder.items(mapSchemaOrRef(items));
        }
        builder.uniqueItems(swaggerSchema.getUniqueItems());

        builder.minItems(swaggerSchema.getMinItems());
        builder.maxItems(swaggerSchema.getMaxItems());

        return builder.build();
    }

    private static void assignType(Schema swaggerSchema, SchemaObject.SchemaObjectBuilder builder, boolean isNullable) {
        Set<String> types =
                swaggerSchema.getTypes() == null ? new HashSet<>() : new HashSet<String>(swaggerSchema.getTypes());
        if (!types.contains(swaggerSchema.getType())) {
            // contradicting types; prefer type for backward compatibility
            // maintainer note: remove condition in next major release
            builder.type(swaggerSchema.getType());
            return;
        }

        if (isNullable) {
            types.add("null");
        }

        builder.type(types);
    }

    /**
     * expects an object representing a schema and tries to unwrap this schema, if it is a
     * ComponentSchema or MultiFormatSchema. The method works recursive on unwrapped Objects.
     *
     * @param schema
     * @return
     */
    private Object unwrapSchema(Object schema) {
        if (schema instanceof ComponentSchema componentSchema) {
            Object unwrappedSchema = componentSchema.getSchema();
            if (unwrappedSchema == null) {
                unwrappedSchema = componentSchema.getReference();
            }
            if (unwrappedSchema == null) {
                unwrappedSchema = componentSchema.getMultiFormatSchema();
            }
            return unwrapSchema(unwrappedSchema);
        }
        if (schema instanceof MultiFormatSchema multiFormatSchema) {
            return unwrapSchema(multiFormatSchema.getSchema());
        }
        return schema;
    }

    /**
     * tries to transform the given schema object to a Swagger Schema. 'schema' may be an asnycapi {@link SchemaObject}
     * or an {@link ComponentSchema} object. If schema is a {@link ComponentSchema} it may contain:
     * <ul>
     *     <li>a {@link SchemaObject} which is handled the same way a directly provided {@link SchemaObject} is handled</li>
     *     <li>a {@link SchemaReference} which is converted to a Swagger Schema with a $ref reference</li>
     *     <li>a {@link MultiFormatSchema}.</li>
     * </ul>
     * if no type is matching, a Runtime Exception is thrown.
     *
     * @param schema Object representing a schema.
     * @return the resulting Schema
     */
    public Schema<?> mapToSwagger(Object schema) {
        // first unwrap ComponentSchema and MultiFormatSchema:
        Object unwrappedSchema = unwrapSchema(schema);

        if (unwrappedSchema instanceof Schema<?> swaggerSchema) {
            return swaggerSchema;
        }
        if (unwrappedSchema instanceof SchemaObject schemaObject) {
            return mapSchemaObjectToSwagger(schemaObject);
        }
        if (unwrappedSchema instanceof SchemaReference schemaReference) {
            return mapSchemaReferenceToSwagger(schemaReference);
        }

        throw new RuntimeException("Could not convert '" + schema + "' to a Swagger Schema");
    }

    /**
     * transforms the given asyncApiSchema {@link SchemaObject} to a Swagger schema object.
     * <p>Note</p>
     * This method does not perform a 'deep' transformation, only the root attributes of asyncApiSchema
     * are mapped to the Swagger schema (best-effort).
     *
     * @param asyncApiSchema
     * @return swagger Schema
     */
    private Schema mapSchemaObjectToSwagger(SchemaObject asyncApiSchema) {
        Schema swaggerSchema = new ObjectSchema();
        swaggerSchema.setName(asyncApiSchema.getTitle());
        swaggerSchema.setTitle(asyncApiSchema.getTitle());

        if (asyncApiSchema.getType() != null) {
            swaggerSchema.setType(asyncApiSchema.getType().stream()
                    .filter(type -> !type.equals(SchemaType.NULL))
                    .findFirst()
                    .orElse(null));
            swaggerSchema.setTypes(asyncApiSchema.getType());
        }
        swaggerSchema.setFormat(asyncApiSchema.getFormat());
        swaggerSchema.setDescription(asyncApiSchema.getDescription());
        swaggerSchema.setExamples(asyncApiSchema.getExamples());
        swaggerSchema.setEnum(asyncApiSchema.getEnumValues());

        if (asyncApiSchema.getProperties() != null) {
            Map<String, Schema> properties = asyncApiSchema.getProperties().entrySet().stream()
                    .map((property) -> Map.entry(property.getKey(), (Schema<?>) mapToSwagger(property.getValue())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            swaggerSchema.setProperties(properties);
        }

        return swaggerSchema;
    }

    /**
     * transforms the given asyncapi {@link SchemaReference} to a Swagger Schema Object.
     *
     * @param asyncApiSchemaReference
     * @return
     */
    private Schema mapSchemaReferenceToSwagger(SchemaReference asyncApiSchemaReference) {
        Schema swaggerSchema = new Schema();
        swaggerSchema.set$ref(asyncApiSchemaReference.getRef());
        return swaggerSchema;
    }
}
