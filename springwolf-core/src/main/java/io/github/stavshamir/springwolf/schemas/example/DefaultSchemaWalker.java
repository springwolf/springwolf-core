// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR;

// TODO: Example JsonGenerator: Traversieren vom Generieren Trennen
// TODO: Welche API brauchen wir beim Generieren? Parentmitgeben vs State im Generator

// TODO: Einfach Yaml generieren?
// TODO: Wie wird der XML Root richtig gesetzt?

@Slf4j
@ConditionalOnProperty(name = SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR, havingValue = "buildin-json", matchIfMissing = true)
@RequiredArgsConstructor
public class DefaultSchemaWalker<T> implements SchemaWalker {

    private final ExampleValueGenerator<T> exampleValueGenerator;

    @Override
    public boolean canHandle(String contentType) {
        return exampleValueGenerator.canHandle(contentType);
    }

    @Override
    public Object fromSchema(Schema schema, Map<String, Schema> definitions) {
        try {
            if (!StringUtils.equals(schema.getType(),"array") && !StringUtils.equals(schema.getType(), "object")) {
                throw  new RuntimeException("Not array or Object");
            }
            exampleValueGenerator.initialize();
            return buildSchemaInternal(schema.getName(), schema, definitions, new HashSet<>());
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build json example for schema {}", schema.getName(), ex);
        }
        return null;
    }

    // TODO remove me
    String buildSchema(Schema schema, Map<String, Schema> definitions) {
        exampleValueGenerator.initialize();
        T finishedSchema = buildSchemaInternal(schema.getName(), schema, definitions, new HashSet<>());
        try {
            return exampleValueGenerator.toString(schema.getName(), finishedSchema);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private T buildSchemaInternal(String name, Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        T exampleValue = getExampleValueFromSchemaAnnotation(schema);
        if (exampleValue != null) {
            return exampleValue;
        }

        String ref = schema.get$ref();
        if (ref != null) {
            String schemaName = StringUtils.substringAfterLast(ref, "/");
            Schema resolvedSchema = definitions.get(schemaName);
            if (resolvedSchema == null) {
                throw new ExampleGeneratingException("Missing schema during example json generation: " + schemaName);
            }
            return buildSchemaInternal(name, resolvedSchema, definitions, visited);
        }

        String type = schema.getType();
        return switch (type) {
            case "array" -> handleArraySchema(schema, definitions, visited); // Handle array schema
            case "boolean" -> exampleValueGenerator.createBooleanExample();
            case "integer" -> exampleValueGenerator.createIntegerExample();
            case "number" -> exampleValueGenerator.createDoubleExample();
            case "object" -> handleObject(name, schema, definitions, visited); // Handle object schema
            case "string" -> handleStringSchema(schema); // Handle string schema
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
        T processedExample = exampleValueGenerator.exampleOrNull(exampleValue);
        if (processedExample != null) {
            return processedExample;
        }

        // Handle special types (i.e. map) with custom @Schema annotation and specified example value
        Object additionalProperties = schema.getAdditionalProperties();
        if (additionalProperties instanceof StringSchema) {
            StringSchema additionalPropertiesSchema = (StringSchema) additionalProperties;
            Object exampleValueString = additionalPropertiesSchema.getExample();
            if (exampleValueString != null) {
                return exampleValueGenerator.createRaw(exampleValueString);
            }
        }

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue instanceof Map) {
            return null;
        }

        // exampleValue is represented in their native type
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
            // exampleValue (i.e. OffsetDateTime) is represented as string
            return exampleValueGenerator.generateStringExample(exampleValue.toString());
        } catch (IllegalArgumentException ex) {
            log.debug("Unable to convert example to JSON: %s".formatted(exampleValue.toString()), ex);
        }

        return exampleValueGenerator.createObjectExample("ignored", Collections.emptyList());
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
                return firstEnumEntry.get(); // TODO: Pass through example value generator
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
                T example = handleObjectProperties(name, properties, definitions, visited);
                visited.remove(schema);
                return example;
            }
        }

        if (schema.getAllOf() != null && !schema.getAllOf().isEmpty()) {
            List<Schema> schemas = schema.getAllOf();
            // TODO lookup ref?
            ObjectNode combinedNode = objectMapper.createObjectNode();
            schemas.stream()
                    .map(s -> buildSchemaInternal(s, definitions, visited))
                    .filter(JsonNode::isObject)
                    .map(JsonNode::fields)
                    .forEach(fields ->
                            fields.forEachRemaining(entry -> combinedNode.set(entry.getKey(), entry.getValue())));

            return combinedNode;
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

    private T handleObjectProperties(
            String name, Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {

        List<Map.Entry<String, T>> propertyList = properties.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    String propertyKey = entry.getKey();
                    T propertyValue = buildSchemaInternal(propertyKey, entry.getValue(), definitions, visited);
                    return Map.entry(propertyKey, propertyValue);
                })
                .toList();

        return exampleValueGenerator.createObjectExample(name, propertyList);
    }
}
