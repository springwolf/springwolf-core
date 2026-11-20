// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.fixtures.SpringwolfApplicationIntegrationTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests loading or ignoring the SpringwolfAutoConfiguration
 */
public class AutoConfigurationIntegrationTest {

    @Nested
    @SpringwolfApplicationIntegrationTest
    class TestSpringwolfEnabled {
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void autoconfigurationShouldBeLoaded() {
            assertThat(asyncApiService).isNotNull();
        }
    }

    @Nested
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    @SpringwolfApplicationIntegrationTest
    class TestSpringwolfDisabled {

        @Autowired
        private ObjectProvider<AsyncApiService> asyncApiServiceObjectProvider;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiServiceObjectProvider.getIfAvailable()).isNull();
        }
    }
}
