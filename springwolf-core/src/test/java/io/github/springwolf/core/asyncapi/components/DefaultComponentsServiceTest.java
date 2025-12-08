// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tools.jackson.core.PrettyPrinter;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DefaultComponentsServiceTest {

    private static final PrettyPrinter prettyPrinter =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));
    private static final JsonMapper jsonMapper = JsonMapper.builder()
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .changeDefaultPropertyInclusion(handler ->
                    JsonInclude.Value.construct(JsonInclude.Include.NON_ABSENT, JsonInclude.Include.NON_ABSENT))
            .defaultPrettyPrinter(prettyPrinter)
            .build();

    @Mock
    private SwaggerSchemaService schemaService;

    private ComponentsService componentsService;

    @BeforeEach
    void setUp() {
        componentsService = new DefaultComponentsService(schemaService);
    }

    @Test
    void getMessages() throws Exception {
        componentsService.registerMessage(
                MessageObject.builder().name("messageName1").build());
        componentsService.registerMessage(
                MessageObject.builder().name("messageName2").build());

        String actualDefinitions =
                jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(componentsService.getMessages());
        String expected =
                """
                        {
                          "messageName1" : {
                            "name" : "messageName1"
                          },
                          "messageName2" : {
                            "name" : "messageName2"
                          }
                        }"""
                        .stripIndent();

        System.out.println("Got: " + actualDefinitions);
        assertThat(actualDefinitions).isEqualTo(expected);
    }
}
