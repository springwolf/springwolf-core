// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.controller;

import io.github.springwolf.core.configuration.SpringwolfWebConfiguration;
import io.github.springwolf.core.controller.ActuatorAsyncApiController;
import io.github.springwolf.core.controller.AsyncApiController;
import io.github.springwolf.core.fixtures.SpringwolfApplicationIntegrationTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringContextControllerIntegrationTest {

    @Nested
    @Import(SpringwolfWebConfiguration.class)
    @SpringwolfApplicationIntegrationTest
    class SpringwolfOnApplicationPortConfigurationTest {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private Optional<AsyncApiController> asyncApiController;

        @Autowired
        private Optional<ActuatorAsyncApiController> actuatorAsyncApiController;

        @Test
        void contextWithApplicationProperties() throws Exception {
            assertThat(context).isNotNull();

            assertThat(asyncApiController).isPresent();
            assertThat(actuatorAsyncApiController).isNotPresent();

            assertThat(asyncApiController.get().asyncApiJson(Optional.empty())).isNotNull();
        }
    }

    @Nested
    @SpringwolfApplicationIntegrationTest
    @Import(SpringwolfWebConfiguration.class)
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
        void contextWithApplicationProperties() throws Exception {
            assertThat(context).isNotNull();

            assertThat(asyncApiController).isNotPresent();
            assertThat(actuatorAsyncApiController).isPresent();

            assertThat(actuatorAsyncApiController.get().asyncApiJson()).isNotNull();
        }
    }
}
