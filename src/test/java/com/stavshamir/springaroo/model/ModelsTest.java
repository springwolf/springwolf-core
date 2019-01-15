package com.stavshamir.springaroo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ModelsTest {

    private Models models = new Models();

    private static final String EXAMPLES_PATH = "/models/examples";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Test
    public void simpleObject() {
        // Given a registered simple object
        // When register is called
        String modelName = models.register(SimpleFoo.class);

        // Then the returned value is the @ApiModel value
        assertThat(modelName)
                .isEqualTo("SimpleFoo");
    }

    @Test
    public void register_annotatedObject() {
        // Given a registered simple object annotated with @ApiModel
        // When register is called
        String modelName = models.register(AnnotatedFoo.class);

        // Then the returned value is the @ApiModel value
        assertThat(modelName)
                .isEqualTo("ApiModelFoo");
    }

    @Test
    public void getExample_simpleObject() {
        // Given a registered simple object
        models.register(SimpleFoo.class);

        // When getExample is called
        String example = models.getExample(SimpleFoo.class);
        String expectedExample = jsonResourceAsWhitespaceStrippedString(EXAMPLES_PATH + "/simple-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getExample_compositeObject() {
        // Given a registered composite object
        models.register(CompositeFoo.class);

        // When getExample is called
        String example = models.getExample(CompositeFoo.class);
        String expectedExample = jsonResourceAsWhitespaceStrippedString(EXAMPLES_PATH + "/composite-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getExample_annotatedObject() {
        // Given a registered simple object annotated with @ApiModel
        models.register(AnnotatedFoo.class);

        // When getExample is called
        String example = models.getExample(AnnotatedFoo.class);
        String expectedExample = jsonResourceAsWhitespaceStrippedString(EXAMPLES_PATH + "/simple-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getDefinitions() throws IOException {
        Map expectedDefinitions = jsonResourceAsMap("/models/definitions.json");

        // Given registered classes
        models.register(CompositeFoo.class);
        models.register(FooWithEnum.class);

        // When getModelsAsJson is called
        String actualDefinitionsJson = objectMapper.writeValueAsString(models.getDefinitions());
        Map actualDefinitions = objectMapper.readValue(actualDefinitionsJson, Map.class);

        // Then it contains the correctly serialized models
        assertThat(actualDefinitions)
                .isEqualTo(expectedDefinitions);
    }

    private String jsonResourceAsWhitespaceStrippedString(String path) {
        InputStream s = this.getClass().getResourceAsStream(path);
        try {
            return IOUtils.toString(s, "UTF-8").replaceAll("\\s+", "");
        } catch (IOException e) {
            fail("Failed to read resource stream");
            return null;
        }
    }

    private Map jsonResourceAsMap(String path) throws IOException {
        InputStream s = this.getClass().getResourceAsStream(path);
        String json = IOUtils.toString(s, "UTF-8");
        return objectMapper.readValue(json, Map.class);
    }

    @Data
    @NoArgsConstructor
    @ApiModel("ApiModelFoo")
    private static class AnnotatedFoo {
        private String s;
        private boolean b;
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