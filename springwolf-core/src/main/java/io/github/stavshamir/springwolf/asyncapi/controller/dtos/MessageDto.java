package io.github.stavshamir.springwolf.asyncapi.controller.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
public class MessageDto {

    private final Map<String, String> bindings;

    private final Map<String, String> headers;

    private final String payload;
    private final String payloadType;

    public Class<?> getPayloadClass() throws ClassNotFoundException {
        return Class.forName(payloadType);
    }
}
