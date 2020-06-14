package io.github.stavshamir.swagger4kafka.schemas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultSchemasServiceTest {

    private final SchemasService schemasService = new DefaultSchemasService();

    private static final String EXAMPLES_PATH = "/schemas/examples";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void simpleObject() throws IOException, JSONException {
        String modelName = schemasService.register(SimpleFoo.class);

        assertThat(modelName)
                .isEqualTo("SimpleFoo");

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/simple-foo.json");

        JSONAssert.assertEquals(expectedExample, example, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void compositeObject() throws IOException, JSONException {
        String modelName = schemasService.register(CompositeFoo.class);

        Schema schema = schemasService.getDefinitions().get(modelName);
        String example = objectMapper.writeValueAsString(schema.getExample());

        String expectedExample = jsonResource(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        JSONAssert.assertEquals(expectedExample, example, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void getDefinitions() throws IOException, JSONException {
        schemasService.register(CompositeFoo.class);
        schemasService.register(FooWithEnum.class);

        String actualDefinitions = objectMapper.writeValueAsString(schemasService.getDefinitions());
        String expected = jsonResource("/schemas/definitions.json");

        JSONAssert.assertEquals(expected, actualDefinitions, JSONCompareMode.NON_EXTENSIBLE);
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

}