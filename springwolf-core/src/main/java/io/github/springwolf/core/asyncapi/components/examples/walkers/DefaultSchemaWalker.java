// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

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
        exampleValueGenerator.initialize();

        String schemaName = exampleValueGenerator.lookupSchemaName(schema);
        try {
            T generatedExample = buildExample(schemaName, schema, definitions, new HashSet<>());

            return exampleValueGenerator.prepareForSerialization(schema, generatedExample);
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build example for schema {}", schemaName, ex);
        }
        return null;
    }

    private T buildExample(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        T exampleValue = getExampleValueFromSchemaAnnotation(schema);
        if (exampleValue != null) {
            return exampleValue;
        }

        Optional<Schema<?>> resolvedSchema = resolveSchemaFromRef(schema, definitions);
        if (resolvedSchema.isPresent()) {
            return buildExample(name, resolvedSchema.get(), definitions, visited);
        }

        String type = schema.getType();
        return switch (type) {
            case "array" -> buildArrayExample(schema, definitions, visited);
            case "boolean" -> exampleValueGenerator.createBooleanExample();
            case "integer" -> exampleValueGenerator.createIntegerExample();
            case "number" -> exampleValueGenerator.createDoubleExample();
            case "object" -> buildFromObjectSchema(name, schema, definitions, visited);
            case "string" -> buildFromStringSchema(schema);
            default -> exampleValueGenerator.createUnknownSchemaStringTypeExample(type);
        };
    }

    private T getExampleValueFromSchemaAnnotation(Schema schema) {
        Object exampleValue = schema.getExample();

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue == null) {
            return null;
        }

        // Return directly, when we have processed this before
        T processedExample =
                exampleValueGenerator.getExampleOrNull(exampleValueGenerator.lookupSchemaName(schema), exampleValue);
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
            return exampleValueGenerator.createStringExample(exampleValue.toString());
        } catch (IllegalArgumentException ex) {
            log.debug("Unable to convert example to JSON: %s".formatted(exampleValue.toString()), ex);
        }

        return exampleValueGenerator.createEmptyObjectExample();
    }

    private T buildArrayExample(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Schema arrayItemSchema =
                resolveSchemaFromRef(schema.getItems(), definitions).orElse(schema.getItems());
        String arrayItemName = exampleValueGenerator.lookupSchemaName(arrayItemSchema);
        T arrayItem = buildExample(arrayItemName, arrayItemSchema, definitions, visited);

        String arrayName = exampleValueGenerator.lookupSchemaName(schema);
        return exampleValueGenerator.createArrayExample(arrayName, arrayItem);
    }

    private T buildFromStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return exampleValueGenerator.createEnumExample(firstEnumValue);
        }

        String format = schema.getFormat();
        if (format == null) {
            return exampleValueGenerator.createStringExample();
        }

        return switch (format) {
            case "date" -> exampleValueGenerator.createDateExample();
            case "date-time" -> exampleValueGenerator.createDateTimeExample();
            case "email" -> exampleValueGenerator.createEmailExample();
            case "password" -> exampleValueGenerator.createPasswordExample();
            case "byte" -> exampleValueGenerator.createByteExample();
            case "binary" -> exampleValueGenerator.createBinaryExample();
            case "uuid" -> exampleValueGenerator.createUuidExample();
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

    private T buildFromObjectSchema(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {
            if (!visited.contains(schema)) {
                visited.add(schema);

                List<PropertyExample<T>> propertyList =
                        buildPropertyExampleListFromSchema(properties, definitions, visited);
                T example = exampleValueGenerator.createObjectExample(name, propertyList);

                visited.remove(schema);
                return example;
            }
        }

        if (schema.getAllOf() != null && !schema.getAllOf().isEmpty()) {
            List<Schema> schemas = schema.getAllOf();

            List<PropertyExample<T>> mergedProperties =
                    buildPropertyExampleListFromSchemas(schemas, definitions, visited);
            return exampleValueGenerator.createObjectExample(name, mergedProperties);
        }
        if (schema.getAnyOf() != null && !schema.getAnyOf().isEmpty()) {
            List<Schema> schemas = schema.getAnyOf();
            Schema anyOfSchema = schemas.get(0);
            return buildExample(name, anyOfSchema, definitions, visited);
        }
        if (schema.getOneOf() != null && !schema.getOneOf().isEmpty()) {
            List<Schema> schemas = schema.getOneOf();
            Schema oneOfSchema = schemas.get(0);
            return buildExample(name, oneOfSchema, definitions, visited);
        }

        // i.e. A MapSchema is type=object, but has properties=null
        return exampleValueGenerator.createEmptyObjectExample();
    }

    private List<PropertyExample<T>> buildPropertyExampleListFromSchema(
            Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {
        return properties.entrySet().stream()
                .map(entry -> {
                    String propertyKey = entry.getKey();
                    T propertyValue = buildExample(propertyKey, entry.getValue(), definitions, visited);
                    return new PropertyExample<>(propertyKey, propertyValue);
                })
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
                throw new ExampleGeneratingException("Missing schema during example json generation: " + schemaName);
            }
            return Optional.of(resolvedSchema);
        }
        return Optional.empty();
    }
}
