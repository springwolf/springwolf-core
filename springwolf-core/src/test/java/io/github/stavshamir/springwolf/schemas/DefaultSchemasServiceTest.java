// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.AsyncApiPayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import io.github.stavshamir.springwolf.schemas.postprocessor.ExampleGeneratorPostProcessor;
import io.github.stavshamir.springwolf.schemas.postprocessor.SchemasPostProcessor;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class DefaultSchemasServiceTest {
    private final SchemasPostProcessor schemasPostProcessor = Mockito.mock(SchemasPostProcessor.class);
    private final SchemasPostProcessor schemasPostProcessor2 = Mockito.mock(SchemasPostProcessor.class);
    private final ComponentsService componentsService = new DefaultComponentsService(
            List.of(),
            List.of(
                    new ExampleGeneratorPostProcessor(new ExampleJsonGenerator()),
                    schemasPostProcessor,
                    schemasPostProcessor2),
            new SwaggerSchemaUtil(),
            new SpringwolfConfigProperties());

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Test
    void getSchemas() throws IOException {
        componentsService.registerSchema(CompositeFoo.class);
        componentsService.registerSchema(FooWithEnum.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
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
    void getDocumentedDefinitions() throws IOException {
        componentsService.registerSchema(DocumentedSimpleFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/documented-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getArrayDefinitions() throws IOException {
        componentsService.registerSchema(ArrayFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/array-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getComplexDefinitions() throws IOException {
        componentsService.registerSchema(ComplexFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/complex-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getListWrapperDefinitions() throws IOException {
        componentsService.registerSchema(ListWrapper.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/generics-wrapper-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void classWithSchemaAnnotation() {
        String modelName = componentsService.registerSchema(ClassWithSchemaAnnotation.class);

        assertThat(modelName).isEqualTo("DifferentName");
    }

    @Test
    void getDefinitionWithFqnClassName() throws IOException {
        // given
        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setUseFqn(true);

        ComponentsService componentsServiceWithFqn =
                new DefaultComponentsService(List.of(), List.of(), new SwaggerSchemaUtil(), properties);

        // when
        Class<?> clazz =
                OneFieldFooWithFqn.class; // swagger seems to cache results. Therefore, a new class must be used.
        componentsServiceWithFqn.registerSchema(clazz);
        String actualDefinitions =
                objectMapper.writer(printer).writeValueAsString(componentsServiceWithFqn.getSchemas());

        // then
        System.out.println("Got: " + actualDefinitions);
        String fqnClassName = clazz.getName();
        assertThat(actualDefinitions).contains(fqnClassName);
        assertThat(fqnClassName.length()).isGreaterThan(clazz.getSimpleName().length());
    }

    @Test
    void postProcessorsAreCalled() {
        componentsService.registerSchema(FooWithEnum.class);

        verify(schemasPostProcessor).process(any(), any());
        verify(schemasPostProcessor2).process(any(), any());
    }

    @Test
    void postProcessorIsSkippedWhenSchemaWasRemoved() {
        doAnswer(invocationOnMock -> {
                    Map<String, io.swagger.v3.oas.models.media.Schema> schemas = invocationOnMock.getArgument(1);
                    schemas.clear();
                    return null;
                })
                .when(schemasPostProcessor)
                .process(any(), any());

        componentsService.registerSchema(FooWithEnum.class);

        verifyNoInteractions(schemasPostProcessor2);
    }

    private String jsonResource(String path) throws IOException {
        InputStream s = this.getClass().getResourceAsStream(path);
        return new String(s.readAllBytes(), StandardCharsets.UTF_8);
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    private static class OneFieldFooWithFqn {
        private String s;
    }

    @Data
    @NoArgsConstructor
    @Schema(description = "foo model")
    private static class DocumentedSimpleFoo {
        @Schema(description = "s field", example = "s value", requiredMode = Schema.RequiredMode.REQUIRED)
        private String s;

        @Schema(example = "1.432", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        private BigInteger bi;

        @Schema(example = "2000-01-01T02:00:00+02:00", requiredMode = Schema.RequiredMode.REQUIRED)
        private OffsetDateTime dt;

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
        private SimpleFoo f;

        @Schema(description = "List without example")
        private List<String> ls_plain;

        @Schema(description = "Map with example", example = "{\"key1\": \"value1\"}")
        private Map<String, String> mss;

        @Schema(description = "Map without example")
        private Map<String, String> mss_plain;
    }

    @Data
    @NoArgsConstructor
    private static class CompositeFoo {
        private String s;
        private SimpleFoo f;
    }

    @Data
    @NoArgsConstructor
    private static class ArrayFoo {
        private List<SimpleFoo> fList;
    }

    @Data
    @NoArgsConstructor
    private static class ListWrapper extends ArrayList<String> {}

    @Data
    @NoArgsConstructor
    private static class FooWithEnum {
        private String s;
        private Bar b;

        private enum Bar {
            BAR1,
            BAR2
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
    private static class ComplexFoo {
        private String s;
        private Boolean b;
        private Integer i;
        private Float f;
        private Double d;
        private OffsetDateTime dt;
        private Nested n;

        @Data
        @NoArgsConstructor
        private static class Nested {
            private String ns;
            private List<Integer> nli;
            private Set<MyClass> nsm;
            private Map<Float, MyClass> nmfm;
            private UUID nu;
            private byte[] nba;
            private Cyclic nc;

            @Data
            @NoArgsConstructor
            private static class Cyclic {

                @Nullable
                private Cyclic cyclic;
            }

            @Data
            @NoArgsConstructor
            private static class MyClass {
                private String s;
            }
        }
    }

    @Nested
    class SchemaWithOneOf {
        @Test
        void testSchemaWithOneOf() throws IOException {
            componentsService.registerSchema(SchemaAnnotationFoo.class);

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = jsonResource("/schemas/annotation-definitions.json");

            System.out.println("Got: " + actualDefinitions);
            assertEquals(expected, actualDefinitions);
        }

        @Data
        @NoArgsConstructor
        public class SchemaAnnotationFoo {
            private String field;
            private AnyOf anyOf;
            private AllOf allOf;
            private OneOf oneOf;
        }

        @Schema(anyOf = {ImplementationOne.class, ImplementationTwo.class})
        public interface AnyOf {}

        @Schema(allOf = {ImplementationOne.class, ImplementationTwo.class})
        public interface AllOf {}

        @Schema(oneOf = {ImplementationOne.class, ImplementationTwo.class})
        public interface OneOf {}

        @Data
        @NoArgsConstructor
        public class ImplementationOne {
            private String firstOne;
            private String secondOne;
        }

        @Data
        @NoArgsConstructor
        public class ImplementationTwo {
            private Integer firstTwo;
            private Boolean secondTwo;
        }
    }

    @Nested
    class AsyncApiPayloadTest {
        @Test
        void stringEnvelopTest() throws IOException {
            componentsService.registerSchema(StringEnvelop.class);

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = jsonResource("/schemas/api-payload.json");

            System.out.println("Got: " + actualDefinitions);
            assertEquals(expected, actualDefinitions);

            assertThat(actualDefinitions).doesNotContain("otherField");
        }

        @Test
        void illegalEnvelopTest() throws IOException {
            componentsService.registerSchema(EnvelopWithMultipleAsyncApiPayloadAnnotations.class);

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());

            // fallback to EnvelopWithMultipleAsyncApiPayloadAnnotations, which contains the field
            assertThat(actualDefinitions).contains("otherField");
        }

        @Data
        @NoArgsConstructor
        public class StringEnvelop {
            Integer otherField;

            @AsyncApiPayload
            @Schema(description = "The payload in the envelop", maxLength = 10)
            String payload;
        }

        @Data
        @NoArgsConstructor
        public class EnvelopWithMultipleAsyncApiPayloadAnnotations {
            @AsyncApiPayload
            Integer otherField;

            @AsyncApiPayload
            @Schema(description = "The payload in the envelop", maxLength = 10)
            String payload;
        }
    }
}
