// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.integrationtests;

import io.github.springwolf.core.asyncapi.DefaultAsyncApiService;
import io.github.springwolf.core.controller.AsyncApiController;
import io.github.springwolf.core.fixtures.SpringwolfIntegrationTest;
import io.github.springwolf.core.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.awaitility.Awaitility.await;

/**
 * Tests the possible init modes (failfast, background)
 */
public class InitModeIntegrationTest {

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.init-mode=fail_fast"})
    @SpringwolfIntegrationTest
    class TestInitModeFailFast {

        @Autowired
        private DefaultAsyncApiService defaultAsyncApiService;

        @Test
        void asyncApiShouldHaveBeenInitializedDuringStartup() {
            // Given initMode is 'fail_fast'
            // When application is ready
            // Then defaultAsyncApiService is initialized
            assertThat(defaultAsyncApiService.isNotInitialized()).isFalse();
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.init-mode=background"})
    @SpringwolfIntegrationTest
    class TestInitModeBackground {

        @Autowired
        private DefaultAsyncApiService defaultAsyncApiService;

        @Test
        void asyncApiApplicationListenerShouldNotBeLoaded() {
            // Given initMode is 'background'
            // When application is ready
            // Then a thread is started in the background and initializes the document
            await().atMost(5, SECONDS).untilAsserted(() -> assertThat(defaultAsyncApiService.isNotInitialized())
                    .isFalse());
        }
    }

    @Nested
    class TestThrowExceptionWhenConfigurationError {

        @Test
        void applicationShouldNotStart() {
            // using title=empty to trigger a validation exception during startup
            String[] args = new String[] {"--springwolf.docket.info.version="};

            try {
                SpringApplication.run(TestApplication.class, args);
                fail("Exception expected, but not raised.");
            } catch (Exception ex) {
                assertThat(ex.getMessage()).contains("Error occurred during creation of AsyncAPI");
            }
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    @SpringwolfIntegrationTest
    class TestSpringwolfDisabled {

        @Autowired(required = false)
        private AsyncApiController asyncApiController;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiController).isNull();
        }
    }
}
