// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import io.github.springwolf.asyncapi.v3.model.ExternalDocumentation;
import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaReference;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utils class providing services to map between Swagger schemas and AsyncApi schemas.
 */
@RequiredArgsConstructor
public class SwaggerSchemaUtil {

    public Map<String, SchemaObject> mapSchemasMap(Map<String, Schema> schemaMap) {
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
     * @param schema the Swagger schema to convert
     * @return ComponentSchema with either a {@link SchemaReference} or a {@link SchemaObject}.
     */
    public ComponentSchema mapSchemaOrRef(Schema schema) {
        if (schema.get$ref() != null) {
            return ComponentSchema.of(new SchemaReference(schema.get$ref()));
        }
        return ComponentSchema.of(mapSchema(schema));
    }

    /**
     * Converts the given Swagger schema to a AsnycApi {@link SchemaObject}. Properties are mapped recursively except
     * as long as the child schemas are 'real' schems and not schema references. So this method performs a deep conversion
     * of the entire Swagger schema.
     *
     * @param value the given Swagger schema instance
     * @return the resulting AsnycApi SchemaObject
     */
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

    /**
     * expects an object representing an schema and tries to unwrap this schema, if it is an
     * ComponentSchema or MultiFormatSchema. The method works recursive on unwrapped Objects.
     *
     * @param schema
     * @return
     */
    public Object unwrapSchema(Object schema) {
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
     * tries to transforms the given schema object to an Swagger Schema. 'schema' may be an asnycapi {@link SchemaObject}
     * or an {@link ComponentSchema} object. If schema is a {@link ComponentSchema} it may contain:
     * <ul>
     *     <li>a {@link SchemaObject} which is handled the same way a directly provided {@link SchemaObject} is handled</li>
     *     <li>a {@link SchemaReference} which is converted to a Swagger Schema with a $ref reference</li>
     *     <li>a {@link MultiFormatSchema}. In this case, these Mediatypes are supported:
     *          <ul>
     *              <li>{@link SchemaFormat#ASYNCAPI_V3}</li>
     *              <li>{@link SchemaFormat#OPENAPI_V3}</li>
     *          </ul>
     *       </li>
     * </ul>
     * if no type is matching, a Runtime Exception is thrown.
     * @param schema Object representing an schema.
     * @return the resulting Schema
     */
    @Nullable
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
     * are mapped to the Swagger schema. The properties of asyncApiSchema will not be mapped to the
     * Swagger schema.
     * @param asyncApiSchema
     * @return
     */
    private Schema mapSchemaObjectToSwagger(SchemaObject asyncApiSchema) {
        Schema swaggerSchema = new Schema();
        swaggerSchema.setType(asyncApiSchema.getType());
        //        swaggerSchema.setFormat(asyncApiSchema.getFormat());
        swaggerSchema.setDescription(asyncApiSchema.getDescription());
        swaggerSchema.setExamples(asyncApiSchema.getExamples());
        swaggerSchema.setEnum(asyncApiSchema.getEnumValues());

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
