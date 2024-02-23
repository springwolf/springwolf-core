// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
    public JsonNode generateDateExample() {
        return this.exampleJsonValueGenerator.generateDateExample();
    }

    @Override
    public JsonNode generateDateTimeExample() {
        return this.exampleJsonValueGenerator.generateDateTimeExample();
    }

    @Override
    public JsonNode generateEmailExample() {
        return this.exampleJsonValueGenerator.generateEmailExample();
    }

    @Override
    public JsonNode generatePasswordExample() {
        return this.exampleJsonValueGenerator.generatePasswordExample();
    }

    @Override
    public JsonNode generateByteExample() {
        return this.exampleJsonValueGenerator.generateByteExample();
    }

    @Override
    public JsonNode generateBinaryExample() {
        return this.exampleJsonValueGenerator.generateBinaryExample();
    }

    @Override
    public JsonNode generateUuidExample() {
        return this.exampleJsonValueGenerator.generateUuidExample();
    }

    @Override
    public JsonNode generateStringExample() {
        return this.exampleJsonValueGenerator.generateStringExample();
    }

    @Override
    public JsonNode generateStringExample(String value) {
        return this.exampleJsonValueGenerator.generateStringExample(value);
    }

    @Override
    public JsonNode generateEnumExample(String anEnumValue) {
        return this.exampleJsonValueGenerator.generateEnumExample(anEnumValue);
    }

    @Override
    public JsonNode generateUnknownSchemaStringTypeExample(String schemaType) {
        return this.exampleJsonValueGenerator.generateUnknownSchemaStringTypeExample(schemaType);
    }

    @Override
    public JsonNode generateUnknownSchemaFormatExample(String schemaFormat) {
        return this.exampleJsonValueGenerator.generateUnknownSchemaFormatExample(schemaFormat);
    }

    @Override
    public JsonNode generateArrayExample(JsonNode arrayItem) {
        return this.exampleJsonValueGenerator.generateArrayExample(arrayItem);
    }

    @Override
    public JsonNode createRaw(Object exampleValueString) {
        return this.exampleJsonValueGenerator.createRaw(exampleValueString);
    }

    @Override
    public JsonNode exampleOrNull(String name, Object example) {
        return this.exampleJsonValueGenerator.exampleOrNull(name, example);
    }
}
