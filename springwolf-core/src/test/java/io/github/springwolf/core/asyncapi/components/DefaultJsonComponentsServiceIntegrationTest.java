// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.examples.SchemaWalkerProvider;
import io.github.springwolf.core.asyncapi.components.examples.walkers.DefaultSchemaWalker;
import io.github.springwolf.core.asyncapi.components.examples.walkers.json.ExampleJsonValueGenerator;
import io.github.springwolf.core.asyncapi.components.postprocessors.ExampleGeneratorPostProcessor;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.github.springwolf.core.asyncapi.schemas.converters.SchemaTitleModelConverter;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.fixtures.ClasspathUtil;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultJsonComponentsServiceIntegrationTest {

    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    private final SwaggerSchemaService schemaService = new SwaggerSchemaService(
            List.of(new SchemaTitleModelConverter()),
            List.of(new ExampleGeneratorPostProcessor(
                    new SchemaWalkerProvider(List.of(new DefaultSchemaWalker<>(new ExampleJsonValueGenerator()))))),
            new SwaggerSchemaUtil(),
            new SpringwolfConfigProperties());
    private final ComponentsService componentsService = new DefaultComponentsService(schemaService);

    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Test
    void getSchemas() throws IOException {
        componentsService.resolvePayloadSchema(CompositeFoo.class, CONTENT_TYPE_APPLICATION_JSON);
        componentsService.resolvePayloadSchema(FooWithEnum.class, CONTENT_TYPE_APPLICATION_JSON);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = loadDefinition("/schemas/json/definitions.json", actualDefinitions);

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getDocumentedDefinitions() throws IOException {
        componentsService.resolvePayloadSchema(DocumentedSimpleFoo.class, CONTENT_TYPE_APPLICATION_JSON);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = loadDefinition("/schemas/json/documented-definitions.json", actualDefinitions);

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getArrayDefinitions() throws IOException {
        componentsService.resolvePayloadSchema(ArrayFoo.class, CONTENT_TYPE_APPLICATION_JSON);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = loadDefinition("/schemas/json/array-definitions.json", actualDefinitions);

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getComplexDefinitions() throws IOException {
        componentsService.resolvePayloadSchema(ComplexFoo.class, CONTENT_TYPE_APPLICATION_JSON);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = loadDefinition("/schemas/json/complex-definitions.json", actualDefinitions);

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getListWrapperDefinitions() throws IOException {
        componentsService.resolvePayloadSchema(ListWrapper.class, CONTENT_TYPE_APPLICATION_JSON);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = loadDefinition("/schemas/json/generics-wrapper-definitions.json", actualDefinitions);

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    private String loadDefinition(String path, String content) throws IOException {
        ClasspathUtil.writeAsActual(path, content);
        return ClasspathUtil.readAsString(path);
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
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

        @Schema(example = "2024-04-24")
        private LocalDate ld;

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

    @NoArgsConstructor
    private static class ListWrapper extends ArrayList<String> {}

    @Data
    @NoArgsConstructor
    private static class FooWithEnum {
        private String s;
        private Bar b;

        @RequiredArgsConstructor
        private enum Bar {
            BAR1(1),
            BAR2(2);

            private final int code;
        }
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
            componentsService.resolvePayloadSchema(SchemaAnnotationFoo.class, CONTENT_TYPE_APPLICATION_JSON);

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = loadDefinition("/schemas/json/annotation-definitions.json", actualDefinitions);

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
    class JsonSubTypesRecursionTest {
        @Test
        void registerSchemaWithoutStackOverflowException() {
            componentsService.resolvePayloadSchema(CriteriaMessage.class, CONTENT_TYPE_APPLICATION_JSON);

            Map<String, SchemaObject> schemas = componentsService.getSchemas();
            assertThat(schemas)
                    .containsOnlyKeys(
                            CriteriaMessage.class.getName().replace("$", "."),
                            LegacyCriteriaMessage.class.getName().replace("$", "."));
        }

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, defaultImpl = CriteriaMessage.class)
        @JsonSubTypes({@JsonSubTypes.Type(value = LegacyCriteriaMessage.class)})
        public class CriteriaMessage {
            private String criteriaId;
            private String note;
            private String expectedValue;
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public class LegacyCriteriaMessage extends CriteriaMessage {
            private List<LegacyCriteriaMessage> subCriteriaList;
        }
    }

    @Nested
    class JsonTypeTest {
        @Test
        void getJsonTypeDefinitions() throws IOException {
            componentsService.resolvePayloadSchema(JsonTypeInfoPayloadDto.class, CONTENT_TYPE_APPLICATION_JSON);

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = loadDefinition("/schemas/json/json-type-definitions.json", actualDefinitions);

            System.out.println("Got: " + actualDefinitions);
            assertEquals(expected, actualDefinitions);
        }

        @Schema(description = "Json Type Info Payload Dto model")
        public record JsonTypeInfoPayloadDto(
                @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED) JsonTypeInfoInterface jsonTypeInfoInterface) {}

        @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, property = "type")
        @JsonSubTypes({
            @JsonSubTypes.Type(value = JsonTypeInfoExampleOne.class, name = "exampleOne"),
            @JsonSubTypes.Type(value = JsonTypeInfoExampleTwo.class, name = "exampleTwo")
        })
        @Schema(oneOf = {JsonTypeInfoExampleOne.class, JsonTypeInfoExampleTwo.class})
        public interface JsonTypeInfoInterface {}

        @JsonTypeName("exampleTwo")
        @Schema(description = "Json Type Info Example Two model")
        public record JsonTypeInfoExampleTwo(
                @Schema(
                                description = "Boo field",
                                example = "booValue",
                                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                        String boo)
                implements JsonTypeInfoInterface {}

        @JsonTypeName("exampleOne")
        @Schema(description = "Json Type Info Example One model")
        public record JsonTypeInfoExampleOne(
                @Schema(
                                description = "Foo field",
                                example = "fooValue",
                                requiredMode = Schema.RequiredMode.NOT_REQUIRED)
                        String foo)
                implements JsonTypeInfoInterface {}
    }
}
