// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import java.util.List;

interface ExampleValueGenerator<T, R> {

    boolean canHandle(String contentType);

    void initialize();

    T createIntegerExample(Integer value);

    T createDoubleExample(Double value);

    T createBooleanExample();

    T createBooleanExample(Boolean value);

    T createIntegerExample();

    T createObjectExample(String name, List<PropertyExample<T>> properties);

    T createEmptyObjectExample();

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

    T generateArrayExample(T arrayItem);

    R serializeIfNeeded(String name, T exampleObject);

    T createRaw(Object exampleValueString);

    T exampleOrNull(String name, Object example);
}
