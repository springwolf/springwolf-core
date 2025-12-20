// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.controller.dtos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

class MessageDtoTest {
    private static final ObjectMapper objectMapper = Json.mapper();

    @Test
    void canBeSerialized() throws Exception {
        String content = "{" + "\"headers\": { \"some-header-key\" : \"some-header-value\" }, "
                + "\"payload\": \"{\\\"some-payload-key\\\":\\\"some-payload-value\\\"}\", "
                + "\"type\": \""
                + MessageDto.class.getCanonicalName() + "\"" + "}";

        MessageDto value = objectMapper.readValue(content, MessageDto.class);

        assertThat(value).isNotNull();
        assertThat(value.getHeaders())
                .isEqualTo(singletonMap("some-header-key", new MessageDto.HeaderValue("some-header-value")));
        assertThat(value.getPayload())
                .isEqualTo(
                        new ObjectMapper().writeValueAsString(singletonMap("some-payload-key", "some-payload-value")));
        assertThat(value.getType()).isEqualTo("io.github.springwolf.core.controller.dtos.MessageDto");
    }

    @Test
    void serializationWithDifferentNamingStrategiesIsIndifferent() throws Exception {
        // https://github.com/springwolf/springwolf-core/issues/1535
        MessageDto messageDto = MessageDto.builder()
                .headers(singletonMap("some-header-key", new MessageDto.HeaderValue("some-header-value")))
                .payload("some-payload-value")
                .type(MessageDto.class.getCanonicalName())
                .build();

        String expected = objectMapper.writeValueAsString(messageDto);
        String actual = objectMapper
                .copy()
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .writeValueAsString(messageDto);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canDeserializeStringHeaderValue() throws Exception {
        // given
        String content = "\"12345\"";

        // when
        MessageDto.HeaderValue value = objectMapper.readValue(content, MessageDto.HeaderValue.class);

        // then
        assertThat(value).isEqualTo(new MessageDto.HeaderValue("12345"));
    }

    @Test
    void canDeserializeNumericHeaderValue() throws Exception {
        // given
        String content = "12345";

        // when
        MessageDto.HeaderValue value = objectMapper.readValue(content, MessageDto.HeaderValue.class);

        // then
        assertThat(value).isEqualTo(new MessageDto.HeaderValue("12345"));
    }

    @Test
    void canSerializeNumericHeaderValue() throws Exception {
        // given
        MessageDto.HeaderValue headerValue = new MessageDto.HeaderValue("12345");

        // when
        JsonNode json = objectMapper.valueToTree(headerValue);

        // then
        assertThat(json.getNodeType()).isEqualTo(JsonNodeType.NUMBER);
    }
}
