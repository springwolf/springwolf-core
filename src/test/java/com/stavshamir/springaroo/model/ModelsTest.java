package com.stavshamir.springaroo.model;

import com.google.common.collect.ImmutableSet;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ModelsTest {

    private Models models = new Models();

    private static final String EXAMPLES_PATH = "/models/examples";
    private static final String MODELS_PATH = "/models/models";

    @Test
    public void getExample_simpleObject() {
        // Given a registered simple object
        models.registerModel(SimpleFoo.class);

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
        models.registerModel(CompositeFoo.class);

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
        models.registerModel(AnnotatedFoo.class);

        // When getExample is called
        String example = models.getExample(AnnotatedFoo.class);
        String expectedExample = jsonResourceAsWhitespaceStrippedString(EXAMPLES_PATH + "/simple-foo.json");

        // Then it returns the correct example object as json
        assertThat(example)
                .isEqualTo(expectedExample);
    }

    @Test
    public void getModelAsJson() {
        Set<String> expectedModels = ImmutableSet
                .of("composite-foo.json", "simple-foo.json", "foo-with-enum.json").stream()
                .map(name -> jsonResourceAsWhitespaceStrippedString(MODELS_PATH + "/" + name))
                .collect(toSet());

        // Given registered classes
        models.registerModel(CompositeFoo.class);
        models.registerModel(FooWithEnum.class);

        // When getModelsAsJson is called
        Set<String> modelsAsJson = models.getModelsAsJson();

        // Then it contains the correctly serialized models
        assertThat(modelsAsJson)
                .isEqualTo(expectedModels);
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