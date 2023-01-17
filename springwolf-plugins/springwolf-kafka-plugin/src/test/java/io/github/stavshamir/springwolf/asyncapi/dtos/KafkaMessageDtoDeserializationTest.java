package io.github.stavshamir.springwolf.asyncapi.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

public class KafkaMessageDtoDeserializationTest {

    @Test
    public void testCanBeSerialized() throws IOException {
        String content = "{\"headers\": { \"some-header-key\" : \"some-header-value\" }, \"payload\": { \"some-payload-key\" : \"some-payload-value\" }}";

        KafkaMessageDto value = new ObjectMapper().readValue(content, KafkaMessageDto.class);

        assertThat(value).isNotNull();
        assertThat(value.getHeaders()).isEqualTo(singletonMap("some-header-key", "some-header-value"));
        assertThat(value.getPayload()).isEqualTo(singletonMap("some-payload-key", "some-payload-value"));
    }
}
