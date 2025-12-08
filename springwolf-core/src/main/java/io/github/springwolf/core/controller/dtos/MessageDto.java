// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller.dtos;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.annotation.JsonDeserialize;

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

    @JsonDeserialize(using = HeaderValueDeserializer.class)
    @JsonSerialize(using = HeaderValueSerializer.class)
    public record HeaderValue(String stringValue) {}

    public static class HeaderValueDeserializer extends ValueDeserializer<HeaderValue> {
        @Override
        public HeaderValue deserialize(JsonParser p, DeserializationContext ctxt) {
            JsonNode node = p.objectReadContext().readTree(p);
            if (node.isNumber()) {
                return new HeaderValue(node.numberValue().toString());
            } else if (node.isString()) {
                return new HeaderValue(node.asString());
            }
            return new HeaderValue(node.toString());
        }
    }

    public static class HeaderValueSerializer extends JsonSerializer<HeaderValue> {
        @Override
        public void serialize(HeaderValue value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
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
