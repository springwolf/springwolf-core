// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.util.Collection;

public class SchemaType {
    public static final String NULL = "null";
    public static final String BOOLEAN = "boolean";
    public static final String OBJECT = "object";
    public static final String ARRAY = "array";
    public static final String NUMBER = "number";
    public static final String STRING = "string";
    public static final String INTEGER = "integer";

    private SchemaType() {}

    public static class Serializer extends ValueSerializer<Object> {

        public Serializer() {}

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializationContext serializers)
                throws JacksonException {
            if (value == null) {
                gen.writeNull();
                return;
            }

            if (value instanceof String) {
                gen.writeString(value.toString());
                return;
            }

            if (value instanceof Collection<?> collection) {
                var stringValues = collection.stream()
                        .filter(v -> v instanceof String)
                        .map(v -> (String) v)
                        .toList();
                if (stringValues.size() == 1) {
                    gen.writeString(stringValues.iterator().next());
                } else {
                    gen.writeArray(stringValues.toArray(new String[0]), 0, collection.size());
                }
            }
        }
    }
}
