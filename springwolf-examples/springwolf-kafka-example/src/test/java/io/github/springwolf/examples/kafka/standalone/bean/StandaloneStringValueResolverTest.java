// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone.bean;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StandaloneStringValueResolverTest {

    private final MockEnvironment environment = new MockEnvironment();
    private final StandaloneStringValueResolver resolver = new StandaloneStringValueResolver(environment);

    @Test
    void resolveEmptyString() {
        // when
        String resolved = resolver.resolveStringValue("");

        // then
        assertThat(resolved).isNotNull().isEmpty();
    }

    @Test
    void resolveNullString() {
        // when
        String resolved = resolver.resolveStringValue(null);

        // then
        assertThat(resolved).isNull();
    }

    @Test
    void resolveSimpleString() {
        // given
        String value = "value";

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo(value);
    }

    @Test
    void resolvePropertyString() {
        // given
        String value = "${property}";
        environment.setProperty("property", "value");

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo("value");
    }

    @Test
    void resolveSpelString() {
        // given
        String value = "#{1 + 1}";

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo("2");
    }

    @Test
    void resolveSpelPropertyString() {
        // given
        String value = "#{${property} + 1}";
        environment.setProperty("property", "1");

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo("2");
    }

    @Test
    void resolveMultipleSpelExpressionString() {
        // given
        String value = "spel with #{'spel expression'} text";

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo("spel with spel expression text");
    }

    @Test
    void resolveInvalidExpressionToOriginalValue() {
        // given
        String value = "invalid expression: #{null}";

        // when
        String resolved = resolver.resolveStringValue(value);

        // then
        assertThat(resolved).isEqualTo("invalid expression: #{null}");
    }
}
