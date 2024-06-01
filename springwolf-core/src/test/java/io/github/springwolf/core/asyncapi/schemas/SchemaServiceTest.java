// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.springwolf.core.asyncapi.components.postprocessors.SchemasPostProcessor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class SchemaServiceTest {

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Mock
    private SchemasPostProcessor schemasPostProcessor;

    @Mock
    private SchemasPostProcessor schemasPostProcessor2;

    private SchemaService schemaService;

    @BeforeEach
    void setUp() {
        schemaService = new SchemaService(
                List.of(),
                List.of(schemasPostProcessor, schemasPostProcessor2),
                new SwaggerSchemaUtil(),
                new SpringwolfConfigProperties());
    }

    @Test
    void classWithSchemaAnnotation() {
        String modelName = schemaService
                .createSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant")
                .schemaName();

        assertThat(modelName).isEqualTo("DifferentName");
    }

    @Test
    void getDefinitionWithoutFqnClassName() throws IOException {
        // given
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setUseFqn(false);

        SchemaService schemaServiceWithFqn =
                new SchemaService(List.of(), List.of(), new SwaggerSchemaUtil(), properties);

        // when
        Class<?> clazz =
                OneFieldFooWithoutFqn.class; // swagger seems to cache results. Therefore, a new class must be used.
        Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaServiceWithFqn
                .createSchema(clazz, "content-type-not-relevant")
                .schemas();
        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemas);

        // then
        System.out.println("Got: " + actualDefinitions);
        assertThat(actualDefinitions).contains(clazz.getSimpleName());
        assertThat(actualDefinitions).doesNotContain(clazz.getCanonicalName());
    }

    @Test
    void postProcessorsAreCalled() {
        schemaService.createSchema(ClassWithSchemaAnnotation.class, "some-content-type");

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

        schemaService.createSchema(ClassWithSchemaAnnotation.class, "content-type-not-relevant");

        verifyNoInteractions(schemasPostProcessor2);
    }

    @Nested
    class createSchema {

        @Test
        void Integer() {
            // when
            Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaService
                    .createSchema(Integer.class, "content-type-not-relevant")
                    .schemas();

            // then
            assertThat(schemas).hasSize(1).containsKey("java.lang.Number");
            assertThat(schemas.get("java.lang.Number").getType()).isEqualTo("number");
        }

        @Test
        void Double() {
            // when
            Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaService
                    .createSchema(Double.class, "content-type-not-relevant")
                    .schemas();

            // then
            assertThat(schemas).hasSize(1).containsKey("java.lang.Number");
            assertThat(schemas.get("java.lang.Number").getType()).isEqualTo("number");
        }

        @Test
        void String() {
            // when
            Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaService
                    .createSchema(String.class, "content-type-not-relevant")
                    .schemas();

            // then
            assertThat(schemas).hasSize(1).containsKey("java.lang.String");
            assertThat(schemas.get("java.lang.String").getType()).isEqualTo("string");
        }

        @Test
        void Byte() {
            // when
            Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaService
                    .createSchema(Byte.class, "content-type-not-relevant")
                    .schemas();

            // then
            assertThat(schemas).hasSize(1).containsKey("java.lang.String");
            assertThat(schemas.get("java.lang.String").getType()).isEqualTo("string");
        }

        @Test
        void Boolean() {
            // when
            Map<String, io.swagger.v3.oas.models.media.Schema> schemas = schemaService
                    .createSchema(Boolean.class, "content-type-not-relevant")
                    .schemas();

            // then
            assertThat(schemas).hasSize(1).containsKey("java.lang.Boolean");
        }
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
    private static class OneFieldFooWithoutFqn {
        private String s;
    }
}
