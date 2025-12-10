// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.controller.dtos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.IOException;
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
    private final String payloadType = String.class.getCanonicalName();

    @Builder.Default
    private final String payload = EMPTY;

    @JsonDeserialize(using = HeaderValueDeserializer.class)
    public record HeaderValue(String stringValue) {}

    public static class HeaderValueDeserializer extends JsonDeserializer<HeaderValue> {
        @Override
        public HeaderValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            if (node.isNumber()) {
                return new HeaderValue(node.numberValue().toString());
            } else if (node.isTextual()) {
                return new HeaderValue(node.textValue());
            }
            return new HeaderValue(node.toString());
        }
    }
}
