// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

// TODO: Einfach Yaml generieren?
@Slf4j
@RequiredArgsConstructor
public class DefaultSchemaWalker<T, R> implements SchemaWalker<R> {

    private final ExampleValueGenerator<T, R> exampleValueGenerator;

    @Override
    public boolean canHandle(String contentType) {
        return exampleValueGenerator.canHandle(contentType);
    }

    @Override
    public R fromSchema(Schema schema, Map<String, Schema> definitions) {
        try {
            exampleValueGenerator.initialize();

            T generatedExample = buildSchemaInternal(schema.getName(), schema, definitions, new HashSet<>());

            return exampleValueGenerator.prepareForSerialization(schema.getName(), generatedExample);
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build example for schema {}", schema.getName(), ex);
        }
        return null;
    }

    private T buildSchemaInternal(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        T exampleValue = getExampleValueFromSchemaAnnotation(schema);
        if (exampleValue != null) {
            return exampleValue;
        }

        Optional<Schema<?>> resolvedSchema = resolveSchemaFromRefIfAny(schema, definitions);
        if (resolvedSchema.isPresent()) {
            return buildSchemaInternal(name, resolvedSchema.get(), definitions, visited);
        }

        String type = schema.getType();
        return switch (type) {
            case "array" -> handleArraySchema(schema, definitions, visited);
            case "boolean" -> exampleValueGenerator.createBooleanExample();
            case "integer" -> exampleValueGenerator.createIntegerExample();
            case "number" -> exampleValueGenerator.createDoubleExample();
            case "object" -> handleObject(name, schema, definitions, visited);
            case "string" -> handleStringSchema(schema);
            default -> exampleValueGenerator.generateUnknownSchemaStringTypeExample(type);
        };
    }

    private T getExampleValueFromSchemaAnnotation(Schema schema) {
        Object exampleValue = schema.getExample();

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue == null) {
            return null;
        }

        // Return directly, when we have processed this before
        T processedExample = exampleValueGenerator.exampleOrNull(schema.getName(), exampleValue);
        if (processedExample != null) {
            return processedExample;
        }

        // Handle special types (i.e. map) with custom @Schema annotation and specified example value
        Object additionalProperties = schema.getAdditionalProperties();
        if (additionalProperties instanceof StringSchema additionalPropertiesSchema) {
            Object exampleValueString = additionalPropertiesSchema.getExample();
            if (exampleValueString != null) {
                return exampleValueGenerator.createRaw(exampleValueString);
            }
        }

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue instanceof Map) {
            return null;
        }

        // value is represented in their native type
        if (exampleValue instanceof Boolean) {
            return exampleValueGenerator.createBooleanExample((Boolean) exampleValue);
        } else if (exampleValue instanceof Number) {
            double doubleValue = ((Number) exampleValue).doubleValue();

            // Check if it's an integer (whole number)
            if (doubleValue == (int) doubleValue) {
                return exampleValueGenerator.createIntegerExample((int) doubleValue);
            }

            return exampleValueGenerator.createDoubleExample(doubleValue);
        }

        try {
            // value (i.e. OffsetDateTime) is represented as string
            return exampleValueGenerator.generateStringExample(exampleValue.toString());
        } catch (IllegalArgumentException ex) {
            log.debug("Unable to convert example to JSON: %s".formatted(exampleValue.toString()), ex);
        }

        return exampleValueGenerator.createEmptyObjectExample();
    }

    private T handleArraySchema(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        T arrayItem = buildSchemaInternal(schema.getName(), schema.getItems(), definitions, visited);

        return exampleValueGenerator.generateArrayExample(arrayItem);
    }

    private T handleStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return exampleValueGenerator.generateEnumExample(firstEnumValue);
        }

        String format = schema.getFormat();
        if (format == null) {
            return exampleValueGenerator.generateStringExample();
        }

        return switch (format) {
            case "date" -> exampleValueGenerator.generateDateExample();
            case "date-time" -> exampleValueGenerator.generateDateTimeExample();
            case "email" -> exampleValueGenerator.generateEmailExample();
            case "password" -> exampleValueGenerator.generatePasswordExample();
            case "byte" -> exampleValueGenerator.generateByteExample();
            case "binary" -> exampleValueGenerator.generateBinaryExample();
            case "uuid" -> exampleValueGenerator.generateUuidExample();
            default -> exampleValueGenerator.generateUnknownSchemaFormatExample(format);
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

    private T handleObject(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        // TODO Handle missconfiguration when object schema has no name
        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            if (!visited.contains(schema)) {
                visited.add(schema);

                List<PropertyExample<T>> propertyList = buildPropertyListFromSchema(properties, definitions, visited);
                T example = exampleValueGenerator.createObjectExample(name, propertyList);

                visited.remove(schema);
                return example;
            }
        }

        if (schema.getAllOf() != null && !schema.getAllOf().isEmpty()) {
            List<Schema> schemas = schema.getAllOf();

            List<PropertyExample<T>> mergedProperties = buildPropertyListFromSchemas(schemas, definitions, visited);
            return exampleValueGenerator.createObjectExample(name, mergedProperties);
        }
        if (schema.getAnyOf() != null && !schema.getAnyOf().isEmpty()) {
            List<Schema> schemas = schema.getAnyOf();
            Schema anyOfSchema = schemas.get(0);
            return buildSchemaInternal(name, anyOfSchema, definitions, visited);
        }
        if (schema.getOneOf() != null && !schema.getOneOf().isEmpty()) {
            List<Schema> schemas = schema.getOneOf();
            Schema oneOfSchema = schemas.get(0);
            return buildSchemaInternal(name, oneOfSchema, definitions, visited);
        }

        // i.e. A MapSchema is type=object, but has properties=null
        return exampleValueGenerator.createEmptyObjectExample();
    }

    private List<PropertyExample<T>> buildPropertyListFromSchema(
            Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {
        return properties.entrySet().stream()
                // TODO: comparing to sort? Remove or add to buildPropertyListFromSchemas
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String propertyKey = entry.getKey();
                    T propertyValue = buildSchemaInternal(propertyKey, entry.getValue(), definitions, visited);
                    return new PropertyExample<>(propertyKey, propertyValue);
                })
                .toList();
    }

    private List<PropertyExample<T>> buildPropertyListFromSchemas(
            List<Schema> schemas, Map<String, Schema> definitions, Set<Schema> visited) {
        return schemas.stream()
                .map(schema -> resolveSchemaFromRefIfAny(schema, definitions).orElse(schema))
                .map(Schema::getProperties)
                .filter(Objects::nonNull)
                .flatMap(propertiesFromSchema ->
                        buildPropertyListFromSchema(propertiesFromSchema, definitions, visited).stream())
                .toList();
    }

    private Optional<Schema<?>> resolveSchemaFromRefIfAny(Schema schema, Map<String, Schema> definitions) {
        String ref = schema.get$ref();
        if (ref != null) {
            String schemaName = MessageReference.extractRefName(ref);
            Schema<?> resolvedSchema = definitions.get(schemaName);
            if (resolvedSchema == null) {
                throw new ExampleGeneratingException("Missing schema during example json generation: " + schemaName);
            }
            return Optional.of(resolvedSchema);
        }
        return Optional.empty();
    }
}
