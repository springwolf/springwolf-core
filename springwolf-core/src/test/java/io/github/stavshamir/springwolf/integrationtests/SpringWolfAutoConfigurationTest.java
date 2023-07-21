package io.github.stavshamir.springwolf.integrationtests;

import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests loading or ignoring the SpringWolfAutoConfiguration
 */
public class SpringWolfAutoConfigurationTest {

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    class TestSpringWolfEnabled {
        @Autowired
        private AsyncApiController asyncApiController;

        @Test
        void autoconfigurationShouldBeLoaded() {
            assertThat(asyncApiController).isNotNull();
        }
    }

    @Nested
    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    class TestSpringWolfDisabled {

        @Autowired
        private ObjectProvider<AsyncApiController> asyncApiControllerObjectProvider;

        @Test
        void autoconfigurationShouldNotBeLoaded() {
            assertThat(asyncApiControllerObjectProvider.getIfAvailable()).isNull();
        }
    }
}
