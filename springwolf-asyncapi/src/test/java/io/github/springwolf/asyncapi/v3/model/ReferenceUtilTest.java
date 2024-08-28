// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReferenceUtilTest {
    @Test
    void shouldCorrectIllegalCharacter() {
        String name = "users/{userId}";

        assertThat(ReferenceUtil.toValidId(name)).isEqualTo("users_{userId}_id");
    }
}
