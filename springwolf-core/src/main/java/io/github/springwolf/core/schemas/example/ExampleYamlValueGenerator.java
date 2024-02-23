// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class ExampleYamlValueGenerator implements ExampleValueGenerator<JsonNode, String> {

    private final Set<String> SUPPORTED_CONTENT_TYPES = Set.of("application/yaml");

    private final ExampleJsonValueGenerator exampleJsonValueGenerator;
    private final ExampleYamlValueSerializer exampleYamlValueSerializer;

    @Override
    public boolean canHandle(String contentType) {
        return SUPPORTED_CONTENT_TYPES.contains(contentType);
    }

    @Override
    public void initialize() {}

    @Override
    public String lookupSchemaName(Schema schema) {
        return exampleJsonValueGenerator.lookupSchemaName(schema);
    }

    @Override
    public String prepareForSerialization(String name, JsonNode exampleObject) {
        try {
            return exampleYamlValueSerializer.writeDocumentAsYamlString(exampleObject);
        } catch (JsonProcessingException e) {
            log.error("Serialize {}", name, e);
            return null;
        }
    }

    @Override
    public JsonNode createIntegerExample(Integer value) {
        return this.exampleJsonValueGenerator.createIntegerExample(value);
    }

    @Override
    public JsonNode createDoubleExample(Double value) {
        return this.exampleJsonValueGenerator.createDoubleExample(value);
    }

    @Override
    public JsonNode createBooleanExample() {
        return this.exampleJsonValueGenerator.createBooleanExample();
    }

    @Override
    public JsonNode createBooleanExample(Boolean value) {
        return this.exampleJsonValueGenerator.createBooleanExample(value);
    }

    @Override
    public JsonNode createIntegerExample() {
        return this.exampleJsonValueGenerator.createIntegerExample();
    }

    @Override
    public JsonNode createObjectExample(String name, List<PropertyExample<JsonNode>> properties) {
        return this.exampleJsonValueGenerator.createObjectExample(name, properties);
    }

    @Override
    public JsonNode createEmptyObjectExample() {
        return this.exampleJsonValueGenerator.createEmptyObjectExample();
    }

    @Override
    public JsonNode createDoubleExample() {
        return this.exampleJsonValueGenerator.createDoubleExample();
    }

    @Override
    public JsonNode createDateExample() {
        return this.exampleJsonValueGenerator.createDateExample();
    }

    @Override
    public JsonNode createDateTimeExample() {
        return this.exampleJsonValueGenerator.createDateTimeExample();
    }

    @Override
    public JsonNode createEmailExample() {
        return this.exampleJsonValueGenerator.createEmailExample();
    }

    @Override
    public JsonNode createPasswordExample() {
        return this.exampleJsonValueGenerator.createPasswordExample();
    }

    @Override
    public JsonNode createByteExample() {
        return this.exampleJsonValueGenerator.createByteExample();
    }

    @Override
    public JsonNode createBinaryExample() {
        return this.exampleJsonValueGenerator.createBinaryExample();
    }

    @Override
    public JsonNode createUuidExample() {
        return this.exampleJsonValueGenerator.createUuidExample();
    }

    @Override
    public JsonNode createStringExample() {
        return this.exampleJsonValueGenerator.createStringExample();
    }

    @Override
    public JsonNode createStringExample(String value) {
        return this.exampleJsonValueGenerator.createStringExample(value);
    }

    @Override
    public JsonNode createEnumExample(String anEnumValue) {
        return this.exampleJsonValueGenerator.createEnumExample(anEnumValue);
    }

    @Override
    public JsonNode createUnknownSchemaStringTypeExample(String schemaType) {
        return this.exampleJsonValueGenerator.createUnknownSchemaStringTypeExample(schemaType);
    }

    @Override
    public JsonNode createUnknownSchemaStringFormatExample(String schemaFormat) {
        return this.exampleJsonValueGenerator.createUnknownSchemaStringFormatExample(schemaFormat);
    }

    @Override
    public JsonNode createArrayExample(JsonNode arrayItem) {
        return this.exampleJsonValueGenerator.createArrayExample(arrayItem);
    }

    @Override
    public JsonNode createRaw(Object exampleValueString) {
        return this.exampleJsonValueGenerator.createRaw(exampleValueString);
    }

    @Override
    public JsonNode getExampleOrNull(String name, Object example) {
        return this.exampleJsonValueGenerator.getExampleOrNull(name, example);
    }
}
