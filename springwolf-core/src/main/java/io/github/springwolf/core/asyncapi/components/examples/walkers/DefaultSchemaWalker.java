// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Default Implementation of {@link SchemaWalker}. Service to build examples for schemas.
 * @param <T>
 * @param <R>
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultSchemaWalker<T, R> implements SchemaWalker<R> {

    private static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final Boolean DEFAULT_BOOLEAN_EXAMPLE = true;

    private static final String DEFAULT_MAP_KEY_EXAMPLE = "key";
    private static final String DEFAULT_STRING_EXAMPLE = "string";
    private static final Integer DEFAULT_INTEGER_EXAMPLE = 0;
    private static final Double DEFAULT_NUMBER_EXAMPLE = 1.1;

    private static final String DEFAULT_DATE_EXAMPLE = "2015-07-20";
    private static final String DEFAULT_DATE_TIME_EXAMPLE = "2015-07-20T15:49:04-07:00";
    private static final String DEFAULT_PASSWORD_EXAMPLE = "string-password";
    private static final String DEFAULT_BYTE_EXAMPLE = "YmFzZTY0LWV4YW1wbGU=";
    private static final String DEFAULT_BINARY_EXAMPLE =
            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001";

    private static final String DEFAULT_EMAIL_EXAMPLE = "example@example.com";
    private static final String DEFAULT_UUID_EXAMPLE = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

    private final ExampleValueGenerator<T, R> exampleValueGenerator;

    @Override
    public boolean canHandle(String contentType) {
        return exampleValueGenerator.canHandle(contentType);
    }

    @Override
    public R fromSchema(Schema schema, Map<String, Schema> definitions) {
        exampleValueGenerator.initialize();

        try {
            Optional<String> schemaName = exampleValueGenerator.lookupSchemaName(schema);

            T generatedExample = buildExample(schemaName, schema, definitions, new HashSet<>())
                    .orElseThrow(() -> new ExampleGeneratingException("Something went wrong"));

            return exampleValueGenerator.prepareForSerialization(schema, generatedExample);
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build example for schema: {}", ex.getMessage(), ex);
        }
        return null;
    }

    private Optional<T> buildExample(
            Optional<String> name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        log.trace("Building example for schema {}", schema);

        Optional<T> exampleValue = getExampleFromSchemaAnnotation(name, schema);
        if (exampleValue.isPresent()) {
            return exampleValue;
        }

        if (visited.contains(schema)) {
            return exampleValueGenerator.createEmptyObjectExample();
        }
        visited.add(schema);
        Optional<T> example = buildExampleFromUnvisitedSchema(name, schema, definitions, visited);
        visited.remove(schema);
        return example;
    }

    private Optional<T> getExampleFromSchemaAnnotation(Optional<String> fieldName, Schema schema) {
        return getExampleValueFromSchemaAnnotation(fieldName, schema, schema.getExample())
                .or(() -> getExampleValueFromSchemaAnnotation(fieldName, schema, schema.getDefault()));
    }

    private Optional<T> getExampleValueFromSchemaAnnotation(
            Optional<String> fieldName, Schema schema, Object exampleValue) {
        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue == null) {
            return Optional.empty();
        }

        // Return directly, when we have processed this before
        T processedExample = exampleValueGenerator.getExampleOrNull(fieldName, schema, exampleValue);
        if (processedExample != null) {
            return Optional.of(processedExample);
        }

        // Handle special types (i.e. map) with custom @Schema annotation and specified example value
        Object additionalProperties = schema.getAdditionalProperties();
        if (additionalProperties instanceof StringSchema additionalPropertiesSchema) {
            Object exampleValueString = additionalPropertiesSchema.getExample();
            if (exampleValueString != null) {
                return Optional.ofNullable(exampleValueGenerator.createRaw(exampleValueString));
            }
        }

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue instanceof Map) {
            return Optional.empty();
        }

        // value is represented in their native type
        if (exampleValue instanceof Boolean booleanValue) {
            return exampleValueGenerator.createBooleanExample(booleanValue, schema);
        }

        if (exampleValue instanceof Number numberValue) {
            double doubleValue = numberValue.doubleValue();

            // Check if it's an integer (whole number)
            if (doubleValue == (int) doubleValue) {
                return exampleValueGenerator.createIntegerExample((int) doubleValue, schema);
            }

            return exampleValueGenerator.createDoubleExample(doubleValue, schema);
        }

        if (exampleValue instanceof Date exampleDate) { // in case of LocalDate, swagger-parser converts it into a Date
            String formatted = ISO_DATE_FORMAT.format(exampleDate);
            return exampleValueGenerator.createStringExample(formatted, schema);
        }

        if (exampleValue instanceof OffsetDateTime exampleOffsetDateTime) {
            String formatted = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(exampleOffsetDateTime);
            return exampleValueGenerator.createStringExample(formatted, schema);
        }

        try {
            // value (i.e. OffsetDateTime) is represented as string
            return exampleValueGenerator.createStringExample(exampleValue.toString(), schema);
        } catch (IllegalArgumentException ex) {
            log.debug("Unable to convert example to JSON: {}", exampleValue, ex);
        }

        return exampleValueGenerator.createEmptyObjectExample();
    }

    /**
     * The caller must ensure that the schema has not been visited before to avoid infinite recursion
     */
    private Optional<T> buildExampleFromUnvisitedSchema(
            Optional<String> name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Optional<Schema<?>> resolvedSchema = resolveSchemaFromRef(schema, definitions);
        if (resolvedSchema.isPresent()) {
            return buildExample(name, resolvedSchema.get(), definitions, visited);
        }

        // future improvement: combine with switch from getType to i.e. instanceOf ComposedSchema, instanceof DateSchema
        Optional<T> composedSchemaExample = buildFromComposedSchema(name, schema, definitions, visited);
        if (composedSchemaExample.isPresent()) {
            return composedSchemaExample;
        }

        // schema may be an openapi v3 or v3.1 schema. While v3 uses an simple 'type' field, v3.1 supports a set of
        // types, for example ["string", "null"].

        String type = getTypeForExampleValue(schema);
        if (type == null) {
            return Optional.empty();
        }

        return switch (type) {
            case "array" -> buildArrayExample(schema, definitions, visited);
            case "boolean" -> exampleValueGenerator.createBooleanExample(DEFAULT_BOOLEAN_EXAMPLE, schema);
            case "integer" -> exampleValueGenerator.createIntegerExample(DEFAULT_INTEGER_EXAMPLE, schema);
            case "number" -> exampleValueGenerator.createDoubleExample(DEFAULT_NUMBER_EXAMPLE, schema);
            case "object" -> buildFromObjectSchema(name, schema, definitions, visited);
            case "string" -> buildFromStringSchema(schema);
            default -> exampleValueGenerator.createUnknownSchemaStringTypeExample(type);
        };
    }

    private Optional<T> buildArrayExample(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Schema arrayItemSchema =
                resolveSchemaFromRef(schema.getItems(), definitions).orElse(schema.getItems());

        Optional<String> arrayName = exampleValueGenerator.lookupSchemaName(schema);

        return exampleValueGenerator
                .lookupSchemaName(arrayItemSchema)
                .or(() -> arrayName)
                .flatMap(arrayItemName ->
                        buildExample(Optional.of(arrayItemName), arrayItemSchema, definitions, visited))
                .map(arrayItem -> exampleValueGenerator.createArrayExample(arrayName, arrayItem));
    }

    private Optional<T> buildFromStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return exampleValueGenerator.createEnumExample(firstEnumValue, schema);
        }

        String format = schema.getFormat();
        if (format == null) {
            return exampleValueGenerator.createStringExample(DEFAULT_STRING_EXAMPLE, schema);
        }

        return switch (format) {
            case "date" -> exampleValueGenerator.createStringExample(DEFAULT_DATE_EXAMPLE, schema);
            case "date-time" -> exampleValueGenerator.createStringExample(DEFAULT_DATE_TIME_EXAMPLE, schema);
            case "email" -> exampleValueGenerator.createStringExample(DEFAULT_EMAIL_EXAMPLE, schema);
            case "password" -> exampleValueGenerator.createStringExample(DEFAULT_PASSWORD_EXAMPLE, schema);
            case "byte" -> exampleValueGenerator.createStringExample(DEFAULT_BYTE_EXAMPLE, schema);
            case "binary" -> exampleValueGenerator.createStringExample(DEFAULT_BINARY_EXAMPLE, schema);
            case "uuid" -> exampleValueGenerator.createStringExample(DEFAULT_UUID_EXAMPLE, schema);
            default -> getFallbackExampleSchemaStringForFormat(format, schema);
        };
    }

    private String getFirstEnumValue(Schema schema) {
        List<String> enums = schema.getEnum();
        if (enums != null) {
            Optional<String> firstEnumEntry = enums.stream().findFirst();
            if (firstEnumEntry.isPresent()) {
                return firstEnumEntry.get();
            }
        }
        return null;
    }

    private Optional<T> getFallbackExampleSchemaStringForFormat(String schemaFormat, Schema schema) {
        return switch (schemaFormat) {
            case "int32", "int64" ->
                exampleValueGenerator.createStringExample(DEFAULT_INTEGER_EXAMPLE.toString(), schema);
            case "float", "double" ->
                exampleValueGenerator.createStringExample(DEFAULT_NUMBER_EXAMPLE.toString(), schema);
            default -> exampleValueGenerator.createUnknownSchemaStringFormatExample(schemaFormat);
        };
    }

    /**
     * looks in schemas openapi-v3 'type' and openapi-v3.1 'types' fields to
     * find the best candidate to use as an example value.
     *
     * @param schema
     * @return the type to use for example values, or null if no suitable type was found.
     */
    @Nullable
    String getTypeForExampleValue(Schema schema) {
        // if the single type field is present, it has precedence over the types field
        if (schema.getType() != null) {
            return schema.getType();
        }

        Set<String> types = schema.getTypes();

        if (types == null || types.isEmpty()) {
            return null;
        }

        return types.stream()
                .filter(t -> !"null".equals(t))
                .sorted() // sort types to be deterministic
                .findFirst()
                .orElse(null);
    }

    private Optional<T> buildFromComposedSchema(
            Optional<String> name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        final List<Schema> schemasAllOf = schema.getAllOf();
        final List<Schema> schemasAnyOf = schema.getAnyOf();
        final List<Schema> schemasOneOf = schema.getOneOf();
        if (!CollectionUtils.isEmpty(schemasAllOf)) {
            return buildFromObjectSchemaWithAllOf(name, schemasAllOf, definitions, visited);
        } else if (!CollectionUtils.isEmpty(schemasAnyOf)) {
            return buildExample(name, schemasAnyOf.get(0), definitions, visited);
        } else if (!CollectionUtils.isEmpty(schemasOneOf)) {
            return buildExample(name, schemasOneOf.get(0), definitions, visited);
        }

        return Optional.empty();
    }

    private Optional<T> buildFromObjectSchema(
            Optional<String> name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        final Optional<T> exampleValue;

        final Map<String, Schema> properties = schema.getProperties();
        final Object additionalProperties = schema.getAdditionalProperties();
        if (properties != null) {
            exampleValue = buildFromObjectSchemaWithProperties(name, properties, definitions, visited);
        } else if (schema instanceof MapSchema && additionalProperties instanceof Schema schema1) {
            exampleValue = buildMapExample(name, schema1, definitions, visited);
        } else {
            exampleValue = exampleValueGenerator.createEmptyObjectExample();
        }

        return exampleValue;
    }

    private Optional<T> buildMapExample(
            Optional<String> name, Schema additionalProperties, Map<String, Schema> definitions, Set<Schema> visited) {
        T object = exampleValueGenerator.startObject(name);
        Map<String, Schema> mapProperties = Map.of(DEFAULT_MAP_KEY_EXAMPLE, additionalProperties);
        exampleValueGenerator.addPropertyExamples(
                object, buildPropertyExampleListFromSchema(mapProperties, definitions, visited));
        exampleValueGenerator.endObject();

        return Optional.of(object);
    }

    private Optional<T> buildFromObjectSchemaWithProperties(
            Optional<String> name,
            Map<String, Schema> properties,
            Map<String, Schema> definitions,
            Set<Schema> visited) {
        T object = exampleValueGenerator.startObject(name);
        exampleValueGenerator.addPropertyExamples(
                object, buildPropertyExampleListFromSchema(properties, definitions, visited));
        exampleValueGenerator.endObject();

        return Optional.of(object);
    }

    private Optional<T> buildFromObjectSchemaWithAllOf(
            Optional<String> name, List<Schema> schemasAllOf, Map<String, Schema> definitions, Set<Schema> visited) {
        T object = exampleValueGenerator.startObject(name);
        exampleValueGenerator.addPropertyExamples(
                object, buildPropertyExampleListFromSchemas(schemasAllOf, definitions, visited));
        exampleValueGenerator.endObject();

        return Optional.of(object);
    }

    private List<PropertyExample<T>> buildPropertyExampleListFromSchema(
            Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {
        return properties.entrySet().stream()
                .map(propertySchema -> {
                    // There can be instances where the schema has no name and only the property is named
                    // in this case we se the key as schema name
                    String propertyKey = exampleValueGenerator
                            .lookupSchemaName(propertySchema.getValue())
                            .orElse(propertySchema.getKey());

                    Optional<T> propertyValue =
                            buildExample(Optional.of(propertyKey), propertySchema.getValue(), definitions, visited);

                    return propertyValue
                            .map(optionalElem -> new PropertyExample<>(propertyKey, optionalElem))
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(PropertyExample::name))
                .toList();
    }

    private List<PropertyExample<T>> buildPropertyExampleListFromSchemas(
            List<Schema> schemas, Map<String, Schema> definitions, Set<Schema> visited) {
        return schemas.stream()
                .map(schema -> resolveSchemaFromRef(schema, definitions).orElse(schema))
                .map(Schema::getProperties)
                .filter(Objects::nonNull)
                .flatMap(propertiesFromSchema ->
                        buildPropertyExampleListFromSchema(propertiesFromSchema, definitions, visited).stream())
                .sorted(Comparator.comparing(PropertyExample::name))
                .toList();
    }

    private Optional<Schema<?>> resolveSchemaFromRef(Schema schema, Map<String, Schema> definitions) {
        String ref = schema.get$ref();
        if (ref != null) {
            String schemaName = ReferenceUtil.getLastSegment(ref);
            Schema<?> resolvedSchema = definitions.get(schemaName);
            if (resolvedSchema == null) {
                throw new ExampleGeneratingException(
                        "Missing schema (name = %s) during example generation".formatted(schemaName));
            }
            return Optional.of(resolvedSchema);
        }
        return Optional.empty();
    }
}
