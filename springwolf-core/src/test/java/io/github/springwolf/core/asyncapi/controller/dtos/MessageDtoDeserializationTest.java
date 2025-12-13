// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.controller.dtos;

import com.fasterxml.jackson.databind.json.JsonMapper;
import io.github.springwolf.core.controller.dtos.MessageDto;
import io.github.springwolf.core.fixtures.JsonMapperTestConfiguration;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonMap;
import static org.assertj.core.api.Assertions.assertThat;

class MessageDtoDeserializationTest {
    private static final JsonMapper jsonMapper = JsonMapperTestConfiguration.jsonMapper;

    @Test
    void canBeSerialized() throws Exception {
        String content = "{" + "\"headers\": { \"some-header-key\" : \"some-header-value\" }, "
                + "\"payload\": \"{\\\"some-payload-key\\\":\\\"some-payload-value\\\"}\", "
                + "\"payloadType\": \""
                + MessageDto.class.getCanonicalName() + "\"" + "}";

        MessageDto value = jsonMapper.readValue(content, MessageDto.class);

        assertThat(value).isNotNull();
        assertThat(value.getHeaders())
                .isEqualTo(singletonMap("some-header-key", new MessageDto.HeaderValue("some-header-value")));
        assertThat(value.getPayload())
                .isEqualTo(jsonMapper.writeValueAsString(singletonMap("some-payload-key", "some-payload-value")));
        assertThat(value.getPayloadType()).isEqualTo("io.github.springwolf.core.controller.dtos.MessageDto");
    }
}
