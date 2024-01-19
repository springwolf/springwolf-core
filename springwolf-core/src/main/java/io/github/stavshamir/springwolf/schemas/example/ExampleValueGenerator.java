// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

// TODO: ExampleValueSerializer?
interface ExampleValueGenerator<T> {

    void initialize();

    T createIntegerExample(Integer value);

    T createDoubleExample(Double value);

    T createBooleanExample();

    T createBooleanExample(Boolean value);

    T createIntegerExample();

    T createObjectExample(String name, List<Map.Entry<String, T>> properties);

    T createDoubleExample();

    T generateDateExample();

    T generateDateTimeExample();

    T generateEmailExample();

    T generatePasswordExample();

    T generateByteExample();

    T generateBinaryExample();

    T generateUuidExample();

    T generateStringExample();

    T generateStringExample(String value);

    T generateEnumExample(String anEnumValue);

    T generateUnknownSchemaStringTypeExample(String schemaType);

    T generateUnknownSchemaFormatExample(String schemaFormat);

    T wrapAsArray(List<T> list);

    String toString(T exampleObject) throws JsonProcessingException;

    T createRaw(Object exampleValueString);
}
