package io.github.stavshamir.springwolf.asyncapi.controller.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

class MessageDtoDeserializationTest {
    private static final ObjectMapper objectMapper = Json.mapper();

    @Test
    void testCanBeSerialized() throws IOException {
        String content =
                "{\"headers\": { \"some-header-key\" : \"some-header-value\" }, \"payload\": { \"some-payload-key\" : \"some-payload-value\" }}";

        MessageDto value = objectMapper.readValue(content, MessageDto.class);

        assertThat(value).isNotNull();
        assertThat(value.getHeaders()).isEqualTo(singletonMap("some-header-key", "some-header-value"));
        assertThat(value.getPayload()).isEqualTo(singletonMap("some-payload-key", "some-payload-value"));
    }
}
