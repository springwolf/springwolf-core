// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas.example;

import java.util.List;

/**
 * Provides the building blocks to generate an example
 *
 * @param <T> The internal representation of a node like object to compose the example
 * @param <R> The serializable representation of the example
 */
interface ExampleValueGenerator<T, R> {

    Boolean DEFAULT_BOOLEAN_EXAMPLE = true;

    String DEFAULT_STRING_EXAMPLE = "string";
    Integer DEFAULT_INTEGER_EXAMPLE = 0;
    Double DEFAULT_NUMBER_EXAMPLE = 1.1;

    String DEFAULT_DATE_EXAMPLE = "2015-07-20";
    String DEFAULT_DATE_TIME_EXAMPLE = "2015-07-20T15:49:04-07:00";
    String DEFAULT_PASSWORD_EXAMPLE = "string-password";
    String DEFAULT_BYTE_EXAMPLE = "YmFzZTY0LWV4YW1wbGU=";
    String DEFAULT_BINARY_EXAMPLE =
            "0111010001100101011100110111010000101101011000100110100101101110011000010110010001111001";

    String DEFAULT_EMAIL_EXAMPLE = "example@example.com";
    String DEFAULT_UUID_EXAMPLE = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

    boolean canHandle(String contentType);

    /**
     * Some internal representation need to be initialized per Schema
     */
    void initialize();

    /**
     * @return The serializable representation of the example (object for json & yaml, string for others)
     */
    R prepareForSerialization(String name, T exampleObject);

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

    T createRaw(Object exampleValueString);

    T exampleOrNull(String name, Object example);
}
