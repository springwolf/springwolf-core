// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

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
import io.swagger.v3.oas.models.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
public class ExampleJsonValueGenerator implements ExampleValueGenerator<JsonNode, JsonNode> {

    private static final Set<String> SUPPORTED_CONTENT_TYPES = Set.of("application/json");
    private static final BooleanNode DEFAULT_BOOLEAN_EXAMPLE =
            BooleanNode.valueOf(ExampleValueGenerator.DEFAULT_BOOLEAN_EXAMPLE);
    private static final ObjectMapper objectMapper = Json.mapper();

    @Override
    public boolean canHandle(String contentType) {
        return SUPPORTED_CONTENT_TYPES.contains(contentType);
    }

    @Override
    public void initialize() {
        // Nothing to do
    }

    @Override
    public String lookupSchemaName(Schema schema) {
        return schema.getName();
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
    public JsonNode createStringExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_STRING_EXAMPLE);
    }

    @Override
    public JsonNode createStringExample(String value) {
        return JsonNodeFactory.instance.textNode(value);
    }

    @Override
    public JsonNode createEnumExample(String anEnumValue) {
        return JsonNodeFactory.instance.textNode(anEnumValue);
    }

    @Override
    public JsonNode createDateExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_DATE_EXAMPLE);
    }

    @Override
    public JsonNode createDateTimeExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_DATE_TIME_EXAMPLE);
    }

    @Override
    public JsonNode createEmailExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_EMAIL_EXAMPLE);
    }

    @Override
    public JsonNode createPasswordExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_PASSWORD_EXAMPLE);
    }

    @Override
    public JsonNode createByteExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_BYTE_EXAMPLE);
    }

    @Override
    public JsonNode createBinaryExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_BINARY_EXAMPLE);
    }

    @Override
    public JsonNode createUuidExample() {
        return JsonNodeFactory.instance.textNode(DEFAULT_UUID_EXAMPLE);
    }

    @Override
    public JsonNode createUnknownSchemaStringTypeExample(String type) {
        return JsonNodeFactory.instance.textNode("unknown schema type: " + type);
    }

    @Override
    public JsonNode createUnknownSchemaStringFormatExample(String schemaFormat) {
        return JsonNodeFactory.instance.textNode("unknown string schema format: " + schemaFormat);
    }

    @Override
    public JsonNode createArrayExample(JsonNode arrayItem) {
        ArrayNode array = objectMapper.createArrayNode();
        array.add(arrayItem);
        return array;
    }

    @Override
    public JsonNode prepareForSerialization(String name, JsonNode exampleObject) {
        return exampleObject;
    }

    @Override
    public JsonNode createRaw(Object exampleValue) {
        try {
            return objectMapper.readTree(exampleValue.toString());
        } catch (JsonProcessingException e) {
            log.info("Unable to parse example to JsonNode: {}", exampleValue, e);
            return null;
        }
    }

    @Override
    public JsonNode getExampleOrNull(String name, Object example) {
        if (example instanceof JsonNode) {
            return (JsonNode) example;
        }

        return null;
    }

    @Override
    public JsonNode createObjectExample(String name, List<PropertyExample<JsonNode>> properties) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        properties.forEach(property -> objectNode.set(property.name(), property.example()));
        return objectNode;
    }

    @Override
    public JsonNode createEmptyObjectExample() {
        return objectMapper.createObjectNode();
    }
}
