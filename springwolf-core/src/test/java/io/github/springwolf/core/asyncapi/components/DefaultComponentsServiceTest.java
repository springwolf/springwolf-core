// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.core.asyncapi.schemas.SchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultComponentsServiceTest {

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Mock
    private SchemaService schemaService;

    private ComponentsService componentsService;

    @BeforeEach
    void setUp() {
        componentsService = new DefaultComponentsService(new SwaggerSchemaUtil(), schemaService);
    }

    @Test
    void getMessages() throws IOException {
        componentsService.registerMessage(
                MessageObject.builder().name("messageName1").build());
        componentsService.registerMessage(
                MessageObject.builder().name("messageName2").build());

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getMessages());
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
        assertEquals(expected, actualDefinitions);
    }
}
