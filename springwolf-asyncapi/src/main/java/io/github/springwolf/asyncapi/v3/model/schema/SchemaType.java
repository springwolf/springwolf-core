// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
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

    public static boolean isPartOfSpec(String type) {
        return switch (type) {
            case NULL, BOOLEAN, OBJECT, ARRAY, NUMBER, STRING, INTEGER -> true;
            default -> false;
        };
    }

    public static class Serializer extends JsonSerializer<Object> {

        public Serializer() {}

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
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
