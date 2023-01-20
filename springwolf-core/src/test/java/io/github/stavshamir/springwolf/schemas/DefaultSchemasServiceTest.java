package io.github.stavshamir.springwolf.schemas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultSchemasServiceTest {

    private final SchemasService schemasService = new DefaultSchemasService(Optional.empty());

    private static final String EXAMPLES_PATH = "/schemas/examples";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void string() throws IOException, JSONException {
        String modelName = schemasService.register(String.class);

        assertThat(modelName)
                .isEqualTo("String");

        assertNotNull(schemasService.getDefinitions().get(modelName));
    }

    @Test
    public void simpleObject() throws IOException, JSONException {
        String modelName = schemasService.register(SimpleFoo.class);

        assertThat(modelName)
                .isEqualTo("SimpleFoo");

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/simple-foo.json");

        JSONAssert.assertEquals(expectedExample, example, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void compositeObject() throws IOException, JSONException {
        String modelName = schemasService.register(CompositeFoo.class);

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        JSONAssert.assertEquals(expectedExample, example, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void getDefinitions() throws IOException, JSONException {
        schemasService.register(CompositeFoo.class);
        schemasService.register(FooWithEnum.class);

        String actualDefinitions = objectMapper.writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/definitions.json");

        JSONAssert.assertEquals(expected, actualDefinitions, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void getDocumentedDefinitions() throws IOException, JSONException {
        schemasService.register(DocumentedSimpleFoo.class);

        String actualDefinitions = objectMapper.writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/documented-definitions.json");

        JSONAssert.assertEquals(expected, actualDefinitions, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void classWithSchemaAnnotation() {
        String modelName = schemasService.register(ClassWithSchemaAnnotation.class);

        assertThat(modelName)
                .isEqualTo("DifferentName");
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
        @io.swagger.v3.oas.annotations.media.Schema(description = "s field", example = "s value", required = true)
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
    private static class FooWithEnum {
        private String s;
        private Bar b;

        private enum Bar {
            BAR1, BAR2
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
