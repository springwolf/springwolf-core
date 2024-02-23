// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.AsyncApiPayload;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.schemas.example.DefaultExampleXmlValueSerializer;
import io.github.stavshamir.springwolf.schemas.example.DefaultSchemaWalker;
import io.github.stavshamir.springwolf.schemas.example.ExampleXmlValueGenerator;
import io.github.stavshamir.springwolf.schemas.example.SchemaWalkerProvider;
import io.github.stavshamir.springwolf.schemas.postprocessor.ExampleGeneratorPostProcessor;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

class DefaultXmlComponentsServiceTest {
    private final ComponentsService componentsService = new DefaultComponentsService(
            List.of(),
            List.of(new ExampleGeneratorPostProcessor(new SchemaWalkerProvider(List.of(
                    new DefaultSchemaWalker<>(new ExampleXmlValueGenerator(new DefaultExampleXmlValueSerializer())))))),
            new SwaggerSchemaUtil(),
            new SpringwolfConfigProperties());
    private static final ObjectMapper objectMapper =
            Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Test
    void getSchemas() throws IOException {
        componentsService.registerSchema(CompositeFoo.class, "text/xml");
        componentsService.registerSchema(FooWithEnum.class, "text/xml");

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/xml/definitions-xml.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getDocumentedDefinitions() throws IOException {
        componentsService.registerSchema(DocumentedSimpleFoo.class, "text/xml");

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/xml/documented-definitions-xml.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getArrayDefinitions() throws IOException {
        componentsService.registerSchema(ArrayFoo.class, "text/xml");

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/xml/array-definitions-xml.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getComplexDefinitions() throws IOException {
        componentsService.registerSchema(ComplexFoo.class, "text/xml");

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/xml/complex-definitions-xml.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getListWrapperDefinitions() throws IOException {
        componentsService.registerSchema(ListWrapper.class, "text/xml");

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
        String expected = jsonResource("/schemas/xml/generics-wrapper-definitions-xml.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    private String jsonResource(String path) throws IOException {
        try (InputStream s = this.getClass().getResourceAsStream(path)) {
            return new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }
    }

    @Data
    @NoArgsConstructor
    @XmlRootElement(name = "SimpleFoo")
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

    @Data
    @NoArgsConstructor
    @Schema(description = "foo model")
    @XmlRootElement(name = "DocumentedSimpleFoo")
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

        @Schema(description = "Map with example", example = "<key1>value1</key1>")
        private Map<String, String> mss;

        @Schema(description = "Map without example")
        private Map<String, String> mss_plain;
    }

    @Data
    @NoArgsConstructor
    @XmlRootElement(name = "CompositeFoo")
    private static class CompositeFoo {
        private String s;
        private SimpleFoo f;
    }

    @Data
    @NoArgsConstructor
    @XmlRootElement(name = "ArrayFoo")
    private static class ArrayFoo {
        private List<SimpleFoo> fList;
    }

    @NoArgsConstructor
    @XmlRootElement(name = "ListWrapper")
    private static class ListWrapper extends ArrayList<String> {}

    @Data
    @NoArgsConstructor
    @XmlRootElement(name = "FooWithEnum")
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
    @XmlRootElement(name = "ComplexFoo")
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
        @XmlRootElement(name = "Nested")
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
            @XmlRootElement(name = "Cyclic")
            private static class Cyclic {

                @Nullable
                private Cyclic cyclic;
            }

            @Data
            @NoArgsConstructor
            @XmlRootElement(name = "MyClass")
            private static class MyClass {
                private String s;
            }
        }
    }

    @Nested
    @XmlRootElement(name = "SchemaWithOneOf")
    class SchemaWithOneOf {
        @Test
        void testSchemaWithOneOf() throws IOException {
            componentsService.registerSchema(SchemaAnnotationFoo.class, "text/xml");

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = jsonResource("/schemas/xml/annotation-definitions-xml.json");

            System.out.println("Got: " + actualDefinitions);
            assertEquals(expected, actualDefinitions);
        }

        @Data
        @NoArgsConstructor
        @XmlRootElement(name = "SchemaAnnotationFoo")
        public class SchemaAnnotationFoo {
            private String field;
            private AnyOf anyOf;
            private AllOf allOf;
            private OneOf oneOf;
        }

        @Schema(anyOf = {ImplementationOne.class, ImplementationTwo.class})
        @XmlRootElement(name = "AnyOf")
        public interface AnyOf {}

        @Schema(allOf = {ImplementationOne.class, ImplementationTwo.class})
        @XmlRootElement(name = "AllOf")
        public interface AllOf {}

        @Schema(oneOf = {ImplementationOne.class, ImplementationTwo.class})
        @XmlRootElement(name = "OneOf")
        public interface OneOf {}

        @Data
        @NoArgsConstructor
        @XmlRootElement(name = "ImplementationOne")
        public class ImplementationOne {
            private String firstOne;
            private String secondOne;
        }

        @Data
        @NoArgsConstructor
        @XmlRootElement(name = "ImplementationTwo")
        public class ImplementationTwo {
            private Integer firstTwo;
            private Boolean secondTwo;
        }
    }

    @Nested
    class AsyncApiPayloadTest {
        @Test
        void stringEnvelopTest() throws IOException {
            componentsService.registerSchema(StringEnvelop.class, "text/xml");

            String actualDefinitions = objectMapper.writer(printer).writeValueAsString(componentsService.getSchemas());
            String expected = jsonResource("/schemas/xml/api-payload-xml.json");

            System.out.println("Got: " + actualDefinitions);
            assertEquals(expected, actualDefinitions);

            assertThat(actualDefinitions).doesNotContain("otherField");
        }

        @Test
        void illegalEnvelopTest() throws IOException {
            componentsService.registerSchema(EnvelopWithMultipleAsyncApiPayloadAnnotations.class, "text/xml");

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
        @XmlRootElement(name = "EnvelopWithMultipleAsyncApiPayloadAnnotations")
        public class EnvelopWithMultipleAsyncApiPayloadAnnotations {
            @AsyncApiPayload
            Integer otherField;

            @AsyncApiPayload
            @Schema(description = "The payload in the envelop", maxLength = 10)
            String payload;
        }
    }
}
