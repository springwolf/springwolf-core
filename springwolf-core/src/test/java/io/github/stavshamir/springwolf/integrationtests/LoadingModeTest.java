package io.github.stavshamir.springwolf.integrationtests;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiApplicationListener;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
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
 * Tests the possible loading modes (failfast, lazy, background)
 */
public class LoadingModeTest {

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    class TestLoadingModeLazy {

        @Autowired(required = false)
        private AsyncApiApplicationListener asyncApiApplicationListener;

        @Autowired
        private DefaultAsyncApiService defaultAsyncApiService;

        @Test
        void asyncApiApplicationListenerShouldNotBeLoaded() {
            assertThat(asyncApiApplicationListener).isNull();
        }

        @Test
        void asyncApiShouldBeInitializedOnFirstGetAsyncAPIInvocation() {
            assertThat(defaultAsyncApiService.isInitialized()).isFalse();
            AsyncAPI asyncAPI = defaultAsyncApiService.getAsyncAPI();
            assertThat(asyncAPI).isNotNull();
            assertThat(defaultAsyncApiService.isInitialized()).isTrue();
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.loading-mode=fail_fast"})
    class TestLoadingModeFailFast {

        @Autowired(required = false)
        private AsyncApiApplicationListener asyncApiApplicationListener;

        @Autowired
        private DefaultAsyncApiService defaultAsyncApiService;

        @Test
        void asyncApiApplicationListenerShouldNotBeLoaded() {
            assertThat(asyncApiApplicationListener).isNull();
        }

        @Test
        void asyncApiShouldHaveBeenInitializedDuringStartup() {
            assertThat(defaultAsyncApiService.isInitialized()).isTrue();
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.loading-mode=background"})
    class TestLoadingModeBackground {

        @Autowired
        private AsyncApiApplicationListener asyncApiApplicationListener;

        @MockBean
        private TaskExecutor taskExecutor;

        @Test
        void asyncApiApplicationListenerShouldNotBeLoaded() {
            assertThat(asyncApiApplicationListener).isNotNull();
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
