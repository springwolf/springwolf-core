// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.processor;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PropertiesUtilTest {

    @Test
    void emptyTest() {
        // given
        String[] strings = {};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void onePropertyTest() {
        // given
        String[] strings = {"key=value"};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEqualTo(Map.of("key", "value"));
    }

    @Test
    void twoPropertiesTest() {
        // given
        String[] strings = {"key1=value1", "key2=value2"};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEqualTo(Map.of("key1", "value1", "key2", "value2"));
    }

    @Test
    void nestedPropertyTest() {
        // given
        String[] strings = {"nested.key=value"};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEqualTo(Map.of("nested", Map.of("key", "value")));
    }

    @Test
    void deeplyNestedPropertyTest() {
        // given
        String[] strings = {"very.deeply.nested.key=value"};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEqualTo(Map.of("very", Map.of("deeply", Map.of("nested", Map.of("key", "value")))));
    }

    @Test
    void yamlSyntaxDoesWorkAsWell() {
        // given
        String[] strings = {"key: value"};

        // when
        Map<String, Object> result = PropertiesUtil.toMap(strings);

        // then
        assertThat(result).isEqualTo(Map.of("key", "value"));
    }
}
