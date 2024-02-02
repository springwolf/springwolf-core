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
import io.swagger.v3.core.util.Json;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class ExampleJsonValueGenerator implements ExampleValueGenerator<JsonNode> {

    private static final BooleanNode DEFAULT_BOOLEAN_EXAMPLE = BooleanNode.TRUE;

    private static final ObjectMapper objectMapper = Json.mapper();
    private static final Double DEFAULT_NUMBER_EXAMPLE = 1.1;
    private static final Integer DEFAULT_INTEGER_EXAMPLE = 0;

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
    public boolean canHandle(String contentType) {
        return (StringUtils.equals(contentType, "application/json"));
    }

    @Override
    public void initialize() {
        // Nothing to do
    }

    @NotNull
    @Override
    public JsonNode createBooleanExample() {
        return DEFAULT_BOOLEAN_EXAMPLE;
    }

    @NotNull
    @Override
    public JsonNode createBooleanExample(Boolean value) {
        return BooleanNode.valueOf(value);
    }

    @Override
    public JsonNode createIntegerExample() {
        return new IntNode(DEFAULT_INTEGER_EXAMPLE);
    }

    @Override
    public JsonNode createDoubleExample() {
        return new DoubleNode(DEFAULT_NUMBER_EXAMPLE);
    }

    @Override
    public JsonNode createIntegerExample(Integer value) {
        return new IntNode(value);
    }

    @Override
    public JsonNode createDoubleExample(Double value) {
        return new DoubleNode(value);
    }

    @Override
    public JsonNode generateStringExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public JsonNode generateStringExample(String value) {
        return JsonNodeFactory.instance.textNode(value);
    }

    @Override
    public JsonNode generateEnumExample(String anEnumValue) {
        return JsonNodeFactory.instance.textNode(anEnumValue);
    }

    @Override
    public JsonNode generateDateExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_DATE_EXAMPLE);
    }

    @Override
    public JsonNode generateDateTimeExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_DATE_TIME_EXAMPLE);
    }

    @Override
    public JsonNode generateEmailExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_EMAIL_EXAMPLE);
    }

    @Override
    public JsonNode generatePasswordExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_PASSWORD_EXAMPLE);
    }

    @Override
    public JsonNode generateByteExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_BYTE_EXAMPLE);
    }

    @Override
    public JsonNode generateBinaryExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_BINARY_EXAMPLE);
    }

    @Override
    public JsonNode generateUuidExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_UUID_EXAMPLE);
    }

    @Override
    public JsonNode generateUnknownSchemaStringTypeExample(String type) {
        return JsonNodeFactory.instance.textNode(DEFAULT_UNKNOWN_SCHEMA_EXAMPLE(type));
    }

    @Override
    public JsonNode generateUnknownSchemaFormatExample(String schemaFormat) {
        return JsonNodeFactory.instance.textNode(DEFAULT_UNKNOWN_SCHEMA_STRING_EXAMPLE(schemaFormat));
    }

    @Override
    public JsonNode generateArrayExample(JsonNode arrayItem) {
        ArrayNode array = objectMapper.createArrayNode();
        array.add(arrayItem);
        return array;
    }

    @Override
    public String toString(String name, JsonNode exampleObject) throws JsonProcessingException {
        return objectMapper.writeValueAsString(exampleObject);
    }

    @Override
    public JsonNode createRaw(Object exampleValue) {
        try {
            return objectMapper.readTree(exampleValue.toString());
        } catch (JsonProcessingException e) {
            log.info("Unable to convert example to JSON: %s".formatted(exampleValue), e);
            return null;
        }
    }

    @Override
    public JsonNode exampleOrNull(Object example) {
        if (example instanceof JsonNode) {
            return (JsonNode) example;
        }

        return null;
    }

    @Override
    public JsonNode createObjectExample(String name, List<Map.Entry<String, JsonNode>> properties) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        properties.forEach(property -> objectNode.set(property.getKey(), property.getValue()));
        return objectNode;
    }

    @Override
    public JsonNode createEmptyObjectExample() {
        return objectMapper.createObjectNode();
    }
}
