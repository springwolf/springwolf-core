// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.messages;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.core.fixtures.JsonMapperTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DefaultMessagesServiceTest {

    private static final JsonMapper jsonMapper = JsonMapperTestConfiguration.jsonMapper;

    private MessagesService messagesService;

    @BeforeEach
    void setUp() {
        messagesService = new DefaultMessagesService();
    }

    @Test
    void getMessages() {
        messagesService.registerMessage(
                MessageObject.builder().name("messageName1").build());
        messagesService.registerMessage(
                MessageObject.builder().name("messageName2").build());

        String actualDefinitions =
                jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(messagesService.getMessages());
        String expected = """
                        {
                          "messageName1" : {
                            "name" : "messageName1"
                          },
                          "messageName2" : {
                            "name" : "messageName2"
                          }
                        }""".stripIndent();

        assertThat(actualDefinitions).isEqualTo(expected);
    }
}
