package com.stavshamir.swagger4kafka.validation;

import com.google.common.collect.ImmutableMap;
import com.stavshamir.swagger4kafka.dtos.ValidationMessage;
import com.stavshamir.swagger4kafka.test.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static com.stavshamir.swagger4kafka.validation.PayloadValidator.validate;
import static org.assertj.core.api.Assertions.assertThat;

public class PayloadValidatorTest {

    private static final String EXAMPLES_PATH = "/models/examples";

    @Test
    public void validate_correctMap() throws IOException {
        Map simpleFooMap = jsonResourceAsMap(EXAMPLES_PATH + "/simple-foo.json");

        ValidationMessage validation = validate(simpleFooMap, SimpleFoo.class.getName());
        assertThat(validation.isValid())
                .isTrue();
    }

    @Test
    public void validate_incorrectBooleanValue() {
        Map<String, Object> simpleFooMap = ImmutableMap.<String, Object>builder()
                .put("s", "sss")
                .put("b", "not false or true")
                .build();

        ValidationMessage validation = validate(simpleFooMap, SimpleFoo.class.getName());

        assertThat(validation.isValid())
                .isFalse();

        assertThat(validation.getMessage())
                .isEqualTo("Cannot deserialize value of type `boolean` from String \"not false or true\": only \"true\" or \"false\" recognized at \"b\"");
    }

    @Test
    public void validate_incorrectEnumValue() {
        Map<String, Object> fooWithEnumMap = ImmutableMap.<String, Object>builder()
                .put("s", "sss")
                .put("b", "wrong")
                .build();

        ValidationMessage validation = validate(fooWithEnumMap, FooWithEnum.class.getName());

        assertThat(validation.isValid())
                .isFalse();

        assertThat(validation.getMessage())
                .isEqualTo("Cannot deserialize value of type `com.stavshamir.swagger4kafka.validation.PayloadValidatorTest$FooWithEnum$Bar` from String \"wrong\": value not one of declared Enum instance names: [BAR1, BAR2] at \"b\"");
    }

    @Test
    public void validate_unrecognizedField() {
        Map<String, Object> simpleFooMap = ImmutableMap.<String, Object>builder()
                .put("s", "sss")
                .put("not b", "not false or true")
                .build();

        ValidationMessage validation = validate(simpleFooMap, SimpleFoo.class.getName());

        assertThat(validation.isValid())
                .isFalse();

        assertThat(validation.getMessage())
                .isEqualTo("Unrecognized field \"not b\"");
    }

    private Map jsonResourceAsMap(String path) throws IOException {
        return Utils.jsonResourceAsMap(this.getClass(), path);
    }

    @Data
    @NoArgsConstructor
    private static class SimpleFoo {
        private String s;
        private int i;
        private boolean b;
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