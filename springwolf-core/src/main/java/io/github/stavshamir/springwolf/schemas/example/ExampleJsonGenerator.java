package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigConstants.SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR;

@Component
@Slf4j
@ConditionalOnProperty(name = SPRINGWOLF_SCHEMA_EXAMPLE_GENERATOR, havingValue = "buildin-json", matchIfMissing = true)
public class ExampleJsonGenerator implements ExampleGenerator {

    private static final ObjectMapper objectMapper = Json.mapper();
    private static final String DEFAULT_NUMBER_EXAMPLE = "1.1";
    private static final String DEFAULT_INTEGER_EXAMPLE = "0";
    private static final String DEFAULT_BOOLEAN_EXAMPLE = "true";
    private static final String DEFAULT_DATE_EXAMPLE = "\"2015-07-20\"";
    private static final String DEFAULT_DATE_TIME_EXAMPLE = "\"2015-07-20T15:49:04-07:00\"";
    private static final String DEFAULT_PASSWORD_EXAMPLE = "\"string-password\"";
    private static final String DEFAULT_BYTE_EXAMPLE = "\"YmFzZTY0LWV4YW1wbGU=\"";
    private static final String DEFAULT_BINARY_EXAMPLE =
            "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"";
    private static final String DEFAULT_STRING_EXAMPLE = "\"string\"";
    private static final String DEFAULT_EMAIL_EXAMPLE = "\"example@example.com\"";
    private static final String DEFAULT_UUID_EXAMPLE = "\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"";

    private static String DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(String type) {
        return "\"unknown schema type: " + type + "\"";
    }

    private static String DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(String format) {
        return "\"unknown string schema format: " + format + "\"";
    }

    @Override
    public Object fromSchema(Schema schema, Map<String, Schema> definitions) {
        try {
            String exampleString = buildSchema(schema, definitions);
            return objectMapper.readValue(exampleString, Object.class);
        } catch (JsonProcessingException | ExampleGeneratingException ex) {
            log.error("Failed to build json example for schema {}", schema.getName());
        }
        return null;
    }

    static String buildSchema(Schema schema, Map<String, Schema> definitions) {
        return buildSchemaInternal(schema, definitions, new HashSet<>());
    }

    private static String buildSchemaInternal(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        String exampleValue = ExampleJsonGenerator.getExampleValueFromSchemaAnnotation(schema);
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
            case "array" -> ExampleJsonGenerator.handleArraySchema(schema, definitions, visited);
            case "boolean" -> DEFAULT_BOOLEAN_EXAMPLE;
            case "integer" -> DEFAULT_INTEGER_EXAMPLE;
            case "number" -> DEFAULT_NUMBER_EXAMPLE;
            case "object" -> ExampleJsonGenerator.handleObject(schema, definitions, visited);
            case "string" -> ExampleJsonGenerator.handleStringSchema(schema);
            default -> DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(type);
        };
    }

    private static String getExampleValueFromSchemaAnnotation(Schema schema) {
        Object exampleValue = schema.getExample();
        if (exampleValue == null) {
            return null;
        }

        // Handle special types (i.e. map) with custom @Schema annotation and specified example value
        Object additionalProperties = schema.getAdditionalProperties();
        if (additionalProperties instanceof StringSchema) {
            StringSchema additionalPropertiesSchema = (StringSchema) additionalProperties;
            Object exampleValueString = additionalPropertiesSchema.getExample();
            if (exampleValueString != null) {
                return (String) exampleValueString;
            }
        }

        // schema is a map of properties from a nested object, whose example cannot be inferred
        if (exampleValue instanceof Map) {
            return null;
        }

        // exampleValue is represented in their native type
        if (exampleValue instanceof Boolean || exampleValue instanceof Number) {
            return exampleValue.toString();
        }

        // exampleValue (i.e. OffsetDateTime) is represented as string
        return "\"" + exampleValue + "\"";
    }

    private static String handleArraySchema(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(buildSchemaInternal(schema.getItems(), definitions, visited));
        sb.append("]");
        return sb.toString();
    }

    private static String handleStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return "\"" + firstEnumValue + "\"";
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

    private static String handleObject(Schema schema, Map<String, Schema> definitions, Set<Schema> visited) {
        Map<String, Schema> properties = schema.getProperties();
        if (properties != null) {

            if (!visited.contains(schema)) {
                visited.add(schema);
                String example = handleObjectProperties(properties, definitions, visited);
                visited.remove(schema);

                return example;
            }
        }
        // i.e. A MapSchema is type=object, but has properties=null
        return "{}";
    }

    private static String handleObjectProperties(
            Map<String, Schema> properties, Map<String, Schema> definitions, Set<Schema> visited) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        String data = properties.entrySet().stream()
                .map(entry -> {
                    StringBuilder propertyStringBuilder = new StringBuilder();
                    propertyStringBuilder.append("\"");
                    propertyStringBuilder.append(entry.getKey());
                    propertyStringBuilder.append("\": ");
                    propertyStringBuilder.append(buildSchemaInternal(entry.getValue(), definitions, visited));
                    return propertyStringBuilder.toString();
                })
                .sorted()
                .collect(Collectors.joining(","));
        sb.append(data);

        sb.append("}");

        return sb.toString();
    }
}
