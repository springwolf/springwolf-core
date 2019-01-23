package com.stavshamir.swagger4kafka.validation;

import com.google.common.collect.ImmutableMap;
import com.stavshamir.swagger4kafka.test.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayloadValidatorTest {

    private static final String EXAMPLES_PATH = "/models/examples";

    @Test
    public void validate_correctMap() throws IOException, ClassNotFoundException {
        Map simpleFooMap = jsonResourceAsMap(EXAMPLES_PATH + "/simple-foo.json");

        PayloadValidator.validate(simpleFooMap, SimpleFoo.class.getName());
    }

    @Test
    public void validate_incorrectValue() {
        Map<String, Object> simpleFooMap = ImmutableMap.<String, Object>builder()
                .put("s", "sss")
                .put("b", "not false or true")
                .build();

        assertThatThrownBy(() -> PayloadValidator.validate(simpleFooMap, SimpleFoo.class.getName()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void unrecognizedField() {
        Map<String, Object> simpleFooMap = ImmutableMap.<String, Object>builder()
                .put("s", "sss")
                .put("not b", "not false or true")
                .build();

        assertThatThrownBy(() -> PayloadValidator.validate(simpleFooMap, SimpleFoo.class.getName()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Map jsonResourceAsMap(String path) throws IOException {
        return Utils.jsonResourceAsMap(this.getClass(), path);
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private boolean b;
    }

}