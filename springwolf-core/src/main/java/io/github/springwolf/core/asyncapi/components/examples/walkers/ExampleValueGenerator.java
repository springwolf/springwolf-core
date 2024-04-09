// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.examples.walkers;

import io.swagger.v3.oas.models.media.Schema;

import java.util.List;
import java.util.Optional;

/**
 * Provides the building blocks to generate an example
 *
 * @param <T> The internal representation of a node like object to compose the example
 * @param <R> The serializable representation of the example
 */
public interface ExampleValueGenerator<T, R> {

    boolean canHandle(String contentType);

    /**
     * Some internal representation need to be initialized per Schema
     */
    default void initialize() {}

    String lookupSchemaName(Schema schema);

    /**
     * @return The serializable representation of the example (object for json and yaml, string for others)
     */
    R prepareForSerialization(Schema name, T exampleObject);

    Optional<T> createIntegerExample(Integer value, T parent, Schema schema);

    Optional<T> createDoubleExample(Double value, T parent, Schema schema);

    Optional<T> createBooleanExample(Boolean value, T parent, Schema schema);

    T createObject(String name);

    void addPropertyExamples(T object, List<PropertyExample<T>> properties);

    Optional<T> createEmptyObjectExample();

    Optional<T> createStringExample(String value, T parent, Schema schema);

    Optional<T> createEnumExample(String anEnumValue, T parent, Schema schema);

    Optional<T> createUnknownSchemaStringTypeExample(String schemaType);

    Optional<T> createUnknownSchemaStringFormatExample(String schemaFormat);

    T createArrayExample(String name, T arrayItem);

    T createRaw(Object exampleValueString);

    T getExampleOrNull(Schema schema, Object example);
}
