// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Optional;

class DummyExampleValueGenerator implements ExampleValueGenerator<Object, Object> {

    @Override
    public boolean canHandle(String contentType) {
        return true;
    }

    @Override
    public Optional<String> lookupSchemaName(Schema<?> schema) {
        return Optional.empty();
    }

    @Override
    public Object prepareForSerialization(Schema<?> name, Object exampleObject) {
        return exampleObject;
    }

    @Override
    public Optional<Object> createIntegerExample(Integer value, Schema<?> schema) {
        return Optional.of("integerExample");
    }

    @Override
    public Optional<Object> createDoubleExample(Double value, Schema<?> schema) {
        return Optional.of("doubleExample");
    }

    @Override
    public Optional<Object> createBooleanExample(Boolean value, Schema<?> schema) {
        return Optional.of("booleanExample");
    }

    @Override
    public Optional createEmptyObjectExample() {
        return Optional.of("emptyObjectExample");
    }

    @Override
    public Optional<Object> createStringExample(String value, Schema<?> schema) {
        return Optional.of(value);
    }

    @Override
    public Optional<Object> createEnumExample(String anEnumValue, Schema<?> schema) {
        return Optional.of(anEnumValue);
    }

    @Override
    public Optional<Object> createUnknownSchemaStringTypeExample(String schemaType) {
        return Optional.empty();
    }

    @Override
    public Optional<Object> createUnknownSchemaStringFormatExample(String schemaFormat) {
        return Optional.empty();
    }

    @Override
    public Object createRaw(Object exampleValueString) {
        return exampleValueString;
    }

    @Override
    public Object getExampleOrNull(Optional<String> fieldName, Schema<?> schema, Object example) {
        return schema.getExample();
    }

    @Override
    public Object createArrayExample(Optional<String> name, Object arrayItem) {
        return null;
    }

    @Override
    public void addPropertyExamples(Object object, List<PropertyExample<Object>> properties) {}

    @Override
    public Object startObject(Optional<String> name) {
        return null;
    }
}
