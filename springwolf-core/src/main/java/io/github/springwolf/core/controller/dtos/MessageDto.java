// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class MessageDto {
    public static final String EMPTY = "";

    private final Map<String, String> bindings;

    private final Map<String, HeaderValue> headers;

    @Builder.Default
    private final String type = String.class.getCanonicalName();

    @Builder.Default
    private final String payload = EMPTY;

    @JsonSerialize(using = HeaderValueSerializer.class)
    public record HeaderValue(String stringValue) {
        @JsonCreator
        public static HeaderValue from(Object value) {
            return new HeaderValue(String.valueOf(value));
        }
    }

    public static class HeaderValueSerializer extends JsonSerializer<HeaderValue> {
        @Override
        public void serialize(HeaderValue value, JsonGenerator gen, SerializerProvider serializers)
                throws java.io.IOException {
            String stringValue = value.stringValue();
            try {
                double number = Double.parseDouble(stringValue);
                gen.writeNumber(number);
            } catch (NumberFormatException e) {
                gen.writeString(stringValue);
            }
        }
    }
}
