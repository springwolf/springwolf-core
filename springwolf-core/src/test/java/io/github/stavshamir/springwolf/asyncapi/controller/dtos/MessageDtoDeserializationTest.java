package io.github.stavshamir.springwolf.asyncapi.controller.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

public class MessageDtoDeserializationTest {

    @Test
    public void testCanBeSerialized() throws IOException {
        String content = "{\"headers\": { \"some-header-key\" : \"some-header-value\" }, \"payload\": { \"some-payload-key\" : \"some-payload-value\" }}";

        MessageDto value = new ObjectMapper().readValue(content, MessageDto.class);

        assertThat(value).isNotNull();
        assertThat(value.getHeaders()).isEqualTo(singletonMap("some-header-key", "some-header-value"));
        assertThat(value.getPayload()).isEqualTo(singletonMap("some-payload-key", "some-payload-value"));
    }
}
