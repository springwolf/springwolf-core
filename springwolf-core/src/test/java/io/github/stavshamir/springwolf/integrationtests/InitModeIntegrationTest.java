package io.github.stavshamir.springwolf.integrationtests;

import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * Tests the possible init modes (failfast, background)
 */
public class InitModeIntegrationTest {

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.init-mode=fail_fast"})
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
    class TestInitModeBackground {

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
    class TestThrowExceptionWhenConfigurationError {

        @Test
        void applicationShouldNotStart() {
            // using title=empty to trigger a validation exception during startup
            String[] args = new String[] {"--test.springwolf.asyncapidocket=false", "--springwolf.docket.info.title="};

            try {
                SpringApplication.run(TestApplication.class, args);
                fail("Exception expected, but not raised.");
            } catch (BeanCreationException ex) {
                assertThat(ex.getMessage()).contains("Error occured during creation of AsyncAPI");
            }
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    class TestSpringwolfDisabled {

        @Autowired(required = false)
        private AsyncApiController asyncApiController;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiController).isNull();
        }
    }
}
