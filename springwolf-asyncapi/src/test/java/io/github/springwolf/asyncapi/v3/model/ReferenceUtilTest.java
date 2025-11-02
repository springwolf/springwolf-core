// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReferenceUtilTest {
    @ParameterizedTest
    @CsvSource(value = {"users/userId", "users~userId", "users*userId", "users#userId"})
    void shouldReplaceIllegalCharacter(String name) {
        String result = ReferenceUtil.toValidId(name);

        assertThat(result).isEqualTo("users_userId");
    }

    @Nested
    class GetLastSegment {
        @Test
        void shouldExtractChannelId() {
            String name = "users/{userId}";

            assertThat(ReferenceUtil.getLastSegment(name)).isEqualTo("{userId}");
        }

        @Test
        void shouldHandleEmptyString() {
            String name = "";

            assertThat(ReferenceUtil.getLastSegment(name)).isEqualTo("");
        }

        @Test
        void shouldReturnOriginalStringWhenAlreadyExtracted() {
            String name = "{userId}";

            assertThat(ReferenceUtil.getLastSegment(name)).isEqualTo(name);
        }
    }
}
