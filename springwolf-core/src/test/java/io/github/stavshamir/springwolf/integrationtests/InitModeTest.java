package io.github.stavshamir.springwolf.integrationtests;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiInitApplicationListener;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Tests the possible init modes (failfast, background)
 */
public class InitModeTest {

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.init-mode=fail_fast"})
    class TestInitModeFailFast {

        @Autowired(required = false)
        private AsyncApiInitApplicationListener asyncApiInitApplicationListener;

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
    class TestInitModeBackground {

        @Autowired
        private AsyncApiInitApplicationListener asyncApiInitApplicationListener;

        @MockBean
        private TaskExecutor taskExecutor;

        @Test
        void asyncApiApplicationListenerShouldNotBeLoaded() {
            // Given initMode is 'background'
            // When application is ready
            // Then taskExecutor.execute has been invoked.
            verify(taskExecutor).execute(any(Runnable.class));
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    class TestSpringWolfDisabled {

        @Autowired(required = false)
        private AsyncApiController asyncApiController;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiController).isNull();
        }
    }
}
