package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
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
    private static final String DEFUALT_DATE_EXAMPLE = "\"2015-07-20\"";
    private static final String DEFAULT_DATE_TIME_EXAMPLE = "\"2015-07-20T15:49:04-07:00\"";
    private static final String DEFAULT_PASSWORD_EXAMPLE = "\"string-password\"";
    private static final String DEFAULT_BYTE_EXAMPLE = "\"YmFzZTY0LWV4YW1wbGU=\"";
    private static final String DEFAULT_BINARY_EXAMPLE =
            "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"";
    private static final String DEFAULT_STRING_EXAMPLE = "\"string\"";

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

        String exampleValue = ExampleJsonGenerator.getExampleValue(schema);
        if (exampleValue != null) {
            return exampleValue;
        }

        String type = schema.getType();
        if (type == null) {
            String schemaName = StringUtils.substringAfterLast(schema.get$ref(), "/");
            Schema resolvedSchema = definitions.get(schemaName);
            if (resolvedSchema == null) {
                throw new ExampleGeneratingException("Missing schema during example json generation: " + schemaName);
            }
            return buildSchema(resolvedSchema, definitions);
        }

        return switch (type) {
            case "array" -> ExampleJsonGenerator.handleArraySchema(schema, definitions);
            case "boolean" -> DEFAULT_BOOLEAN_EXAMPLE;
            case "integer" -> DEFAULT_INTEGER_EXAMPLE;
            case "number" -> DEFAULT_NUMBER_EXAMPLE;
            case "object" -> ExampleJsonGenerator.handleObject(schema, definitions);
            case "string" -> ExampleJsonGenerator.handleStringSchema(schema);
            default -> "unknown schema type: " + type;
        };
    }

    private static String getExampleValue(Schema schema) {
        Object exampleValue = schema.getExample();
        if (exampleValue instanceof BigDecimal) {
            return exampleValue.toString();
        } else if (exampleValue instanceof Boolean) {
            return exampleValue.toString();
        } else if (exampleValue instanceof Number) {
            return exampleValue.toString();
        } else if (exampleValue instanceof String) {
            return "\"" + exampleValue + "\"";
        }
        return null;
    }

    private static String handleArraySchema(Schema schema, Map<String, Schema> definitions) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(buildSchema(schema.getItems(), definitions));
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
            case "date" -> DEFUALT_DATE_EXAMPLE;
            case "date-time" -> DEFAULT_DATE_TIME_EXAMPLE;
            case "password" -> DEFAULT_PASSWORD_EXAMPLE;
            case "byte" -> DEFAULT_BYTE_EXAMPLE;
            case "binary" -> DEFAULT_BINARY_EXAMPLE;
            default -> "unknown type format: " + format;
        };
    }

    private static String getFirstEnumValue(Schema schema) {
        if (schema.getEnum() != null) {
            Optional<String> firstEnumEntry = schema.getEnum().stream().findFirst();
            if (firstEnumEntry.isPresent()) {
                return firstEnumEntry.get();
            }
        }
        return null;
    }

    private static String handleObject(Schema schema, Map<String, Schema> definitions) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        Map<String, Schema> properties = schema.getProperties();
        String data = properties.entrySet().stream()
                .map(entry -> {
                    StringBuilder propertyStringBuilder = new StringBuilder();
                    propertyStringBuilder.append("\"");
                    propertyStringBuilder.append(entry.getKey());
                    propertyStringBuilder.append("\": ");
                    propertyStringBuilder.append(buildSchema(entry.getValue(), definitions));
                    return propertyStringBuilder.toString();
                })
                .sorted()
                .collect(Collectors.joining(","));
        sb.append(data);

        sb.append("}");

        return sb.toString();
    }
}
