package io.github.stavshamir.springwolf.asyncapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.stavshamir.springwolf.fixtures.MinimalTestContextConfiguration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringContextControllerIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @Import(value = {AsyncApiController.class, ActuatorAsyncApiController.class})
    @MinimalTestContextConfiguration
    class SpringwolfOnApplicationPortConfigurationTest {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private Optional<AsyncApiController> asyncApiController;

        @Autowired
        private Optional<ActuatorAsyncApiController> actuatorAsyncApiController;

        @Test
        void testContextWithApplicationProperties() throws JsonProcessingException {
            assertNotNull(context);

            assertThat(asyncApiController).isPresent();
            assertThat(actuatorAsyncApiController).isNotPresent();

            assertThat(asyncApiController.get().asyncApiJson()).isNotNull();
        }
    }

    @Nested
    @ExtendWith(SpringExtension.class)
    @MinimalTestContextConfiguration
    @Import(value = {AsyncApiController.class, ActuatorAsyncApiController.class})
    @TestPropertySource(
            properties = {"springwolf.endpoint.actuator.enabled=true", "management.endpoints.web.exposure.include=*"})
    class SpringwolfOnManagementPortConfigurationTest {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private Optional<AsyncApiController> asyncApiController;

        @Autowired
        private Optional<ActuatorAsyncApiController> actuatorAsyncApiController;

        @Test
        void testContextWithApplicationProperties() throws JsonProcessingException {
            assertNotNull(context);

            assertThat(asyncApiController).isNotPresent();
            assertThat(actuatorAsyncApiController).isPresent();

            assertThat(actuatorAsyncApiController.get().asyncApiJson()).isNotNull();
        }
    }
}
