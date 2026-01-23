// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.schema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collection;

@Getter
@RequiredArgsConstructor
public enum SchemaType {
    NULL("null"),
    BOOLEAN("boolean"),
    OBJECT("object"),
    ARRAY("array"),
    NUMBER("number"),
    STRING("string"),
    INTEGER("integer");

    private final String value;

    public static SchemaType fromValue(String value) {
        for (SchemaType b : SchemaType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Serializer extends JsonSerializer<Object> {
        public Serializer() {}

        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }

            if (value instanceof SchemaType schemaType) {
                gen.writeString(schemaType.getValue());
                return;
            }

            if (value instanceof String str) {
                gen.writeString(str);
                return;
            }

            if (value instanceof Collection<?> collection) {
                var stringValues = collection.stream()
                        .map(v -> v instanceof SchemaType st ? st.getValue() : v.toString())
                        .toList();
                if (stringValues.size() == 1) {
                    gen.writeString(stringValues.iterator().next());
                } else {
                    gen.writeArray(stringValues.toArray(new String[0]), 0, stringValues.size());
                }
            }
        }
    }
}
