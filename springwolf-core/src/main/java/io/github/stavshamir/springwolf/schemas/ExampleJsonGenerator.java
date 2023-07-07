package io.github.stavshamir.springwolf.schemas;

import io.swagger.v3.oas.models.media.Schema;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;


// TODO: use default value
public class ExampleJsonGenerator {

    public static String fromSchema(Schema schema, Map<String, Schema> definitions) {

        if (schema.getType() == null) {
            String schemaName = StringUtils.substringAfterLast(schema.get$ref(), "/");
            Schema resolvedSchema = definitions.get(schemaName);
            return fromSchema(resolvedSchema, definitions);
        }

        return switch (schema.getType()) {
            // TODO: array
            case "boolean" -> handleBoolean(schema);
            case "integer" -> handleInteger(schema);
            case "number" -> handleNumber(schema);
            case "object" -> handleObject(schema, definitions);
            case "string" -> handleStringSchema(schema);
            case "array" -> handleArraySchema(schema, definitions);
            default -> "unknown schema type: "+schema.getType();
        };

    }

    private static String handleArraySchema(Schema schema, Map<String, Schema> definitions) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(fromSchema(schema.getItems(), definitions));
        sb.append(" ]");
        return sb.toString();
    }

    private static String handleInteger(Schema schema) {
        return getExampleOrDefault(schema,"0");
    }

    private static String handleNumber(Schema schema) {
        return getExampleOrDefault(schema, "1.1");
    }

    private static String handleBoolean(Schema schema) {
        return getExampleOrDefault(schema, "true");
    }

    private static String handleStringSchema(Schema schema) {
        String format = schema.getFormat(); // TODO:

        if (format == null) {
            return "\"string\"";
        }

        return switch (format) {
            case "date" -> "\"2006-01-02\"";
            case "date-time" -> "\"2006-01-02T15:04:05Z07:00\"";
            case "password" -> "\"string-password\"";
            case "byte" -> "\"YmFzZTY0LWV4YW1wbGU=\"";
            case "binary" -> "\"0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001\"";
            default -> "unknown type format: " + format;
        };
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

    private static String getExampleOrDefault(Schema schema, String defaultValue) {
        Object exampleValue = schema.getExample();
        if (exampleValue instanceof String) {
            return (String)exampleValue;
        }

        return defaultValue;
    }

}
