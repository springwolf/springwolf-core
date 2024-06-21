// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@RequiredArgsConstructor
public class DefaultSchemaWalker<T, R> implements SchemaWalker<R> {

    private static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    Boolean DEFAULT_BOOLEAN_EXAMPLE = true;

    String DEFAULT_STRING_EXAMPLE = "string";
    Integer DEFAULT_INTEGER_EXAMPLE = 0;
    Double DEFAULT_NUMBER_EXAMPLE = 1.1;

    String DEFAULT_DATE_EXAMPLE = "2015-07-20";
    String DEFAULT_DATE_TIME_EXAMPLE = "2015-07-20T15:49:04-07:00";
    String DEFAULT_PASSWORD_EXAMPLE = "string-password";
    String DEFAULT_BYTE_EXAMPLE = "YmFzZTY0LWV4YW1wbGU=";
    String DEFAULT_BINARY_EXAMPLE =
            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001";

    String DEFAULT_EMAIL_EXAMPLE = "example@example.com";
    String DEFAULT_UUID_EXAMPLE = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

    private final ExampleValueGenerator<T, R> exampleValueGenerator;

    @Override
    public boolean canHandle(String contentType) {
        return exampleValueGenerator.canHandle(contentType);
    }

    @Override
    public R fromSchema(Schema schema, Map<String, Schema> definitions) {
        exampleValueGenerator.initialize();

        try {
            String schemaName = exampleValueGenerator
                    .lookupSchemaName(schema)
                    .orElseThrow(() ->
                            new ExampleGeneratingException("There is no name set for Schema: " + schema.toString()));

            T generatedExample = buildExample(schemaName, schema, definitions, new HashSet<>())
                    .orElseThrow(() -> new ExampleGeneratingException("Something went wrong"));

            return exampleValueGenerator.prepareForSerialization(schema, generatedExample);
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build example for schema: {}", ex.getMessage(), ex);
        }
        return null;
    }

    private Optional<T> buildExample(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Optional<T> exampleValue = getExampleValueFromSchemaAnnotation(schema);
        if (exampleValue.isPresent()) {
            return exampleValue;
        }

        Optional<Schema<?>> resolvedSchema = resolveSchemaFromRef(schema, definitions);
        if (resolvedSchema.isPresent()) {
            return buildExample(name, resolvedSchema.get(), definitions, visited);
        }

        String type = schema.getType();
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

    private Optional<T> getExampleValueFromSchemaAnnotation(Schema schema) {
        Object exampleValue = schema.getExample();

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue == null) {
            return Optional.empty();
        }

        // Return directly, when we have processed this before
        T processedExample = exampleValueGenerator.getExampleOrNull(schema, exampleValue);
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

    private Optional<T> buildArrayExample(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Schema arrayItemSchema =
                resolveSchemaFromRef(schema.getItems(), definitions).orElse(schema.getItems());

        Optional<String> arrayName = exampleValueGenerator.lookupSchemaName(schema);

        return exampleValueGenerator
                .lookupSchemaName(arrayItemSchema)
                .or(() -> arrayName)
                .flatMap(arrayItemName -> buildExample(arrayItemName, arrayItemSchema, definitions, visited))
                .map(arrayItem -> exampleValueGenerator.createArrayExample(
                        arrayName.orElseThrow(
                                () -> new ExampleGeneratingException("Array schema does not have a name: " + schema)),
                        arrayItem));
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
            default -> exampleValueGenerator.createUnknownSchemaStringFormatExample(format);
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

    private Optional<T> buildFromObjectSchema(
            String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Map<String, Schema> properties = schema.getProperties();
        if (visited.contains(schema)) {
            return exampleValueGenerator.createEmptyObjectExample();
        }
        visited.add(schema);

        // i.e. A MapSchema is type=object, but has properties=null
        Optional<T> exampleValue = exampleValueGenerator.createEmptyObjectExample();
        if (properties != null) {
            T object = exampleValueGenerator.startObject(name);

            List<PropertyExample<T>> propertyList =
                    buildPropertyExampleListFromSchema(properties, definitions, visited);
            exampleValueGenerator.addPropertyExamples(object, propertyList);

            exampleValueGenerator.endObject();

            exampleValue = Optional.of(object);
        } else if (schema.getAllOf() != null && !schema.getAllOf().isEmpty()) {
            List<Schema> schemas = schema.getAllOf();

            T object = exampleValueGenerator.startObject(name);

            List<PropertyExample<T>> mergedProperties =
                    buildPropertyExampleListFromSchemas(schemas, definitions, visited);
            exampleValueGenerator.addPropertyExamples(object, mergedProperties);

            exampleValueGenerator.endObject();

            exampleValue = Optional.of(object);
        } else if (schema.getAnyOf() != null && !schema.getAnyOf().isEmpty()) {
            List<Schema> schemas = schema.getAnyOf();
            Schema anyOfSchema = schemas.get(0);
            exampleValue = buildExample(name, anyOfSchema, definitions, visited);
        } else if (schema.getOneOf() != null && !schema.getOneOf().isEmpty()) {
            List<Schema> schemas = schema.getOneOf();
            Schema oneOfSchema = schemas.get(0);
            exampleValue = buildExample(name, oneOfSchema, definitions, visited);
        }

        visited.remove(schema);

        return exampleValue;
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
                            buildExample(propertyKey, propertySchema.getValue(), definitions, visited);

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
            String schemaName = MessageReference.extractRefName(ref);
            Schema<?> resolvedSchema = definitions.get(schemaName);
            if (resolvedSchema == null) {
                throw new ExampleGeneratingException("Missing schema during example generation: " + schemaName);
            }
            return Optional.of(resolvedSchema);
        }
        return Optional.empty();
    }
}
