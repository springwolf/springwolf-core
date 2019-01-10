package com.stavshamir.springaroo.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelsTest {

    private Models models = new Models();

    @Test
    public void getExample_simpleObject() {
        String example = models.getExample(SimpleFoo.class);
        assertThat(example)
                .isEqualTo("{\n" +
                        "  \"s\" : \"string\",\n" +
                        "  \"b\" : true\n" +
                        "}");
    }

    @Test
    public void getExample_composedObject() {
        String example = models.getExample(ComposedFoo.class);
        assertThat(example)
                .isEqualTo("{\n" +
                        "  \"s\" : \"string\",\n" +
                        "  \"f\" : {\n" +
                        "    \"s\" : \"string\",\n" +
                        "    \"b\" : true\n" +
                        "  }\n" +
                        "}");
    }

    @Test
    public void getExample_annotatedObject() {
        String example = models.getExample(AnnotatedFoo.class);
        assertThat(example)
                .isEqualTo("{\n" +
                        "  \"s\" : \"string\",\n" +
                        "  \"b\" : true\n" +
                        "}");
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
    private static class ComposedFoo {
        private String s;
        private SimpleFoo f;
    }

}