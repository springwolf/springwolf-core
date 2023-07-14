package io.github.stavshamir.springwolf.schemas;

import io.swagger.v3.oas.models.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class ExampleJsonGenerator {

    public static String fromSchema(Schema schema, Map<String, Schema> definitions) {
        String exampleValue = getExampleValue(schema);
        if (exampleValue != null) {
            return exampleValue;
        }

        String type = schema.getType();
        if (type == null) {
            String schemaName = StringUtils.substringAfterLast(schema.get$ref(), "/");
            Schema resolvedSchema = definitions.get(schemaName);
            return fromSchema(resolvedSchema, definitions);
        }

        return switch (type) {
            case "array" -> handleArraySchema(schema, definitions);
            case "boolean" -> handleBoolean(schema);
            case "integer" -> handleInteger(schema);
            case "number" -> handleNumber(schema);
            case "object" -> handleObject(schema, definitions);
            case "string" -> handleStringSchema(schema);
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
        sb.append("[ ");
        sb.append(fromSchema(schema.getItems(), definitions));
        sb.append(" ]");
        return sb.toString();
    }

    private static String handleInteger(Schema schema) {
        return "5";
    }

    private static String handleNumber(Schema schema) {
        return "1.1";
    }

    private static String handleBoolean(Schema schema) {
        return "true";
    }

    private static String handleStringSchema(Schema schema) {
        String firstEnumValue = getFirstEnumValue(schema);
        if (firstEnumValue != null) {
            return "\"" + firstEnumValue + "\"";
        }

        String format = schema.getFormat();

        if (format == null) {
            return "\"string\"";
        }

        return switch (format) {
            case "date" -> "\"2006-01-02\"";
            case "date-time" -> "\"2006-01-02T15:04:05Z07:00\"";
            case "password" -> "\"string-password\"";
            case "byte" -> "\"YmFzZTY0LWV4YW1wbGU=\"";
            case "binary" ->
                    "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"";
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
                    propertyStringBuilder.append(fromSchema(entry.getValue(), definitions));
                    return propertyStringBuilder.toString();
                })
                .sorted()
                .collect(Collectors.joining(","));
        sb.append(data);

        sb.append("}");

        return sb.toString();
    }
}
