package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultSchemasServiceTest {

    private final SchemasService schemasService = new DefaultSchemasService(Optional.empty());

    private static final String EXAMPLES_PATH = "/schemas/examples";
    private static final ObjectMapper objectMapper = Json.mapper();
    private static final PrettyPrinter printer =
            new DefaultPrettyPrinter().withObjectIndenter(new DefaultIndenter("  ", DefaultIndenter.SYS_LF));

    @Test
    void string() throws IOException {
        String modelName = schemasService.register(String.class);

        assertThat(modelName).isEqualTo("String");

        assertNotNull(schemasService.getDefinitions().get(modelName));

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writer(printer).writeValueAsString(schema.getExample());
        String expectedExample = "\"string\"";

        assertThat(example).isEqualTo(expectedExample);
    }

    @Test
    void simpleObject() throws IOException {
        String modelName = schemasService.register(SimpleFoo.class);

        assertThat(modelName).isEqualTo("SimpleFoo");

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writer(printer).writeValueAsString(schema.getExample());
        String expectedExample = jsonResource(EXAMPLES_PATH + "/simple-foo.json");

        assertEquals(expectedExample, example);
    }

    @Test
    void compositeObject() throws IOException {
        String modelName = schemasService.register(CompositeFoo.class);

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writer(printer).writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        assertEquals(expectedExample, example);
    }

    @Test
        // TODO: move to ExampleJsonGeneratorTest
    void arrayObject() throws IOException {
        String modelName = schemasService.register(ArrayFoo.class);

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writer(printer).writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        assertEquals(expectedExample, example);
    }

    @Test
        // TODO: move to ExampleJsonGeneratorTest
    void arraySimple() throws IOException {
        String modelName = schemasService.register(SimpleArrayFoo.class);

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writer(printer).writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        assertEquals(expectedExample, example);
    }

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
    void getDocumentedDefinitions() throws IOException, JSONException {
        schemasService.register(DocumentedSimpleFoo.class);

        String actualDefinitions = objectMapper.writer(printer).writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/documented-definitions.json");

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
        return IOUtils.toString(s, StandardCharsets.UTF_8);
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
    private static class SimpleArrayFoo {
        private List<String> fList;
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
