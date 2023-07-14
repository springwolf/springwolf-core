package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.v3.core.util.Json;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultSchemasServiceTest {

    private final SchemasService schemasService = new DefaultSchemasService(Optional.empty());

    private static final ObjectMapper objectMapper = Json.mapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));


    @Test
    void getDefinitions() throws IOException {
        schemasService.register(CompositeFoo.class);
        schemasService.register(FooWithEnum.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getDocumentedDefinitions() throws IOException {
        schemasService.register(DocumentedSimpleFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/documented-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void getArrayDefinitions() throws IOException {
        schemasService.register(ArrayFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/array-definitions.json");

        System.out.println("Got: " + actualDefinitions);
        assertEquals(expected, actualDefinitions);
    }

    @Test
    void classWithSchemaAnnotation() {
        String modelName = schemasService.register(ClassWithSchemaAnnotation.class);

        assertThat(modelName).isEqualTo("DifferentName");
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
    @io.swagger.v3.oas.annotations.media.Schema(description = "foo model")
    private static class DocumentedSimpleFoo {
        @io.swagger.v3.oas.annotations.media.Schema(
                description = "s field",
                example = "s value",
                requiredMode = io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED)
        private String s;
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
    @io.swagger.v3.oas.annotations.media.Schema(name = "DifferentName")
    private static class ClassWithSchemaAnnotation {
        private String s;
        private boolean b;
    }
}
