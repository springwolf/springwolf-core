// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class DefaultComponentsServiceTest {

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Mock
    private SchemasPostProcessor schemasPostProcessor;

    @Mock
    private SchemasPostProcessor schemasPostProcessor2;

    private ComponentsService componentsService;

    @BeforeEach
    void setUp() {
        componentsService = new DefaultComponentsService(
                List.of(),
                List.of(schemasPostProcessor, schemasPostProcessor2),
                new SwaggerSchemaUtil(),
                new SpringwolfConfigProperties());
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

    @Test
    void classWithSchemaAnnotation() {
        String modelName =
                componentsService.registerSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant");

        assertThat(modelName).isEqualTo("DifferentName");
    }

    @Test
    void getDefinitionWithoutFqnClassName() throws IOException {
        // given
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setUseFqn(false);

        ComponentsService componentsServiceWithFqn =
                new DefaultComponentsService(List.of(), List.of(), new SwaggerSchemaUtil(), properties);

        // when
        Class<?> clazz =
                OneFieldFooWithFqn.class; // swagger seems to cache results. Therefore, a new class must be used.
        componentsServiceWithFqn.registerSchema(clazz, "content-type-not-relevant");
        String actualDefinitions =
                objectMapper.writer(printer).writeValueAsString(componentsServiceWithFqn.getSchemas());

        // then
        System.out.println("Got: " + actualDefinitions);
        assertThat(actualDefinitions).contains(clazz.getSimpleName());
        assertThat(actualDefinitions).doesNotContain(clazz.getCanonicalName());
    }

    @Test
    void postProcessorsAreCalled() {
        componentsService.registerSchema(ClassWithSchemaAnnotation.class, "some-content-type");

        verify(schemasPostProcessor).process(any(), any(), eq("some-content-type"));
        verify(schemasPostProcessor2).process(any(), any(), eq("some-content-type"));
    }

    @Test
    void postProcessorIsSkippedWhenSchemaWasRemoved() {
        doAnswer(invocationOnMock -> {
                    Map<String, Schema> schemas = invocationOnMock.getArgument(1);
                    schemas.clear();
                    return null;
                })
                .when(schemasPostProcessor)
                .process(any(), any(), any());

        componentsService.registerSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant");

        verifyNoInteractions(schemasPostProcessor2);
    }

    @Data
    @NoArgsConstructor
    @Schema(name = "DifferentName")
    private static class ClassWithSchemaAnnotation {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    private static class OneFieldFooWithFqn {
        private String s;
    }
}
