// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests;

import io.github.springwolf.core.controller.AsyncApiController;
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
        private AsyncApiController asyncApiController;

        @Test
        void autoconfigurationShouldBeLoaded() {
            assertThat(asyncApiController).isNotNull();
        }
    }

    @Nested
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    @SpringwolfApplicationIntegrationTest
    class TestSpringwolfDisabled {

        @Autowired
        private ObjectProvider<AsyncApiController> asyncApiControllerObjectProvider;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiControllerObjectProvider.getIfAvailable()).isNull();
        }
    }
}
