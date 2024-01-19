// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.RawValue;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR;

@Slf4j
@ConditionalOnProperty(name = SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR, havingValue = "buildin-json", matchIfMissing = true)
public class ExampleJsonGenerator implements ExampleGenerator {

    private static final ObjectMapper objectMapper = Json.mapper();
    private static final Double DEFAULT_NUMBER_EXAMPLE = 1.1;
    private static final Integer DEFAULT_INTEGER_EXAMPLE = 0;
    private static final BooleanNode DEFAULT_BOOLEAN_EXAMPLE = BooleanNode.TRUE;
    private static final String DEFAULT_DATE_EXAMPLE = "2015-07-20";
    private static final String DEFAULT_DATE_TIME_EXAMPLE = "2015-07-20T15:49:04-07:00";
    private static final String DEFAULT_PASSWORD_EXAMPLE = "string-password";
    private static final String DEFAULT_BYTE_EXAMPLE = "YmFzZTY0LWV4YW1wbGU=";
    private static final String DEFAULT_BINARY_EXAMPLE =
            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001";
    private static final String DEFAULT_STRING_EXAMPLE = "string";
    private static final String DEFAULT_EMAIL_EXAMPLE = "example@example.com";
    private static final String DEFAULT_UUID_EXAMPLE = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

    private static String DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(String type) {
        return "unknown schema type: " + type;
    }

    private static String DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(String format) {
        return "unknown string schema format: " + format;
    }

    @Override
    public Object fromSchema(Schema schema, Map<String, Schema> definitions) {
        try {
            return buildSchemaInternal(schema, definitions, new HashSet<>());
        } catch (ExampleGeneratingException ex) {
            log.info("Failed to build json example for schema {}", schema.getName(), ex);
        }
        return null;
    }

    static String buildSchema(Schema schema, Map<String, Schema> definitions) {
        return buildSchemaInternal(schema, definitions, new HashSet<>()).toString();
    }

    private static JsonNode buildSchemaInternal(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        JsonNode exampleValue = ExampleJsonGenerator.getExampleValueFromSchemaAnnotation(schema);
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
            return buildSchemaInternal(resolvedSchema, definitions, visited);
        }

        String type = schema.getType();
        return switch (type) {
            case "array" -> ExampleJsonGenerator.handleArraySchema(schema, definitions, visited); // Handle array schema
            case "boolean" -> DEFAULT_BOOLEAN_EXAMPLE;
            case "integer" -> new IntNode(DEFAULT_INTEGER_EXAMPLE);
            case "number" -> new DoubleNode(DEFAULT_NUMBER_EXAMPLE);
            case "object" -> ExampleJsonGenerator.handleObject(schema, definitions, visited); // Handle object schema
            case "string" -> JsonNodeFactory.instance.textNode(handleStringSchema(schema)); // Handle string schema
            default -> JsonNodeFactory.instance.textNode(DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(type));
        };
    }

    private static JsonNode getExampleValueFromSchemaAnnotation(Schema schema) {
        Object exampleValue = schema.getExample();

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue == null) {
            return null;
        }

        // Return directly, when we have processed this before
        if (exampleValue instanceof JsonNode) {
            return (JsonNode) exampleValue;
        }

        // Create an ObjectNode to hold the example JSON
        JsonNode exampleNode = objectMapper.createObjectNode();

        // Handle special types (i.e. map) with custom @Schema annotation and specified example value
        Object additionalProperties = schema.getAdditionalProperties();
        if (additionalProperties instanceof StringSchema) {
            StringSchema additionalPropertiesSchema = (StringSchema) additionalProperties;
            Object exampleValueString = additionalPropertiesSchema.getExample();
            if (exampleValueString != null) {
                try {
                    exampleNode = objectMapper.readTree(exampleValueString.toString());
                } catch (JsonProcessingException ex) {
                    log.debug("Unable to convert example to JSON: %s".formatted(exampleValue.toString()), ex);
                }
                return exampleNode;
            }
        }

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue instanceof Map) {
            return null;
        }

        // exampleValue is represented in their native type
        if (exampleValue instanceof Boolean) {
            return (Boolean) exampleValue ? BooleanNode.TRUE : BooleanNode.FALSE;
        } else if (exampleValue instanceof Number) {
            double doubleValue = ((Number) exampleValue).doubleValue();

            // Check if it's an integer (whole number)
            if (doubleValue == (int) doubleValue) {
                return new IntNode((int) doubleValue);
            }

            return new DoubleNode(doubleValue);
        }

        try {
            // exampleValue (i.e. OffsetDateTime) is represented as string
            exampleNode = JsonNodeFactory.instance.textNode(exampleValue.toString());
        } catch (IllegalArgumentException ex) {
            log.debug("Unable to convert example to JSON: %s".formatted(exampleValue.toString()), ex);
        }

        return exampleNode;
    }

    private static ArrayNode handleArraySchema(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();

        List<JsonNode> list = Arrays.asList(buildSchemaInternal(schema.getItems(), definitions, visited));

        for (JsonNode node : list) arrayNode.add(node);

        return arrayNode;
    }

    private static String handleStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return firstEnumValue;
        }

        String format = schema.getFormat();

        if (format == null) {
            return DEFAULT_STRING_EXAMPLE;
        }

        return switch (format) {
            case "date" -> DEFAULT_DATE_EXAMPLE;
            case "date-time" -> DEFAULT_DATE_TIME_EXAMPLE;
            case "email" -> DEFAULT_EMAIL_EXAMPLE;
            case "password" -> DEFAULT_PASSWORD_EXAMPLE;
            case "byte" -> DEFAULT_BYTE_EXAMPLE;
            case "binary" -> DEFAULT_BINARY_EXAMPLE;
            case "uuid" -> DEFAULT_UUID_EXAMPLE;
            default -> DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(format);
        };
    }

    private static String getFirstEnumValue(Schema schema) {
        List<String> enums = schema.getEnum();
        if (enums != null) {
            Optional<String> firstEnumEntry = enums.stream().findFirst();
            if (firstEnumEntry.isPresent()) {
                return firstEnumEntry.get();
            }
        }
        return null;
    }

    private static JsonNode handleObject(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {

        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {

            if (!visited.contains(schema)) {
                visited.add(schema);
                ObjectNode example = handleObjectProperties(properties, definitions, visited);
                visited.remove(schema);

                return example;
            }
        }

        if (schema.getAllOf() != null && !schema.getAllOf().isEmpty()) {
            List<Schema> schemas = schema.getAllOf();
            // Open: Handle properties of all schemas, not only the first one
            return buildSchemaInternal(schemas.get(0), definitions, visited);
        }
        if (schema.getAnyOf() != null && !schema.getAnyOf().isEmpty()) {
            List<Schema> schemas = schema.getAnyOf();
            return buildSchemaInternal(schemas.get(0), definitions, visited);
        }
        if (schema.getOneOf() != null && !schema.getOneOf().isEmpty()) {
            List<Schema> schemas = schema.getOneOf();
            return buildSchemaInternal(schemas.get(0), definitions, visited);
        }

        // i.e. A MapSchema is type=object, but has properties=null
        return objectMapper.createObjectNode();
    }

    private static ObjectNode handleObjectProperties(
            Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {

        ObjectNode objectNode = objectMapper.createObjectNode();

        properties.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            String propertyKey = entry.getKey();
            RawValue propertyRawValue = new RawValue(buildSchemaInternal(entry.getValue(), definitions, visited));
            objectNode.putRawValue(propertyKey, propertyRawValue);
        });

        return objectNode;
    }
}
