// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.configuration;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.amqp.controller.SpringwolfAmqpController;
import io.github.springwolf.plugins.amqp.producer.SpringwolfAmqpProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringValueResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfAmqpProducerConfigurationIntegrationTest {

    @SpringJUnitConfig(
            classes = {
                SpringwolfAmqpAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.amqp.publishing.enabled=true"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                AsyncApiService.class,
                RabbitTemplate.class
            })
    @Nested
    class AmqpProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfAmqpProducer> springwolfAmqpProducer;

        @Autowired
        private Optional<SpringwolfAmqpController> springwolfAmqpController;

        @Test
        void springwolfAmqpProducerShouldBePresentInSpringContext() {
            assertThat(springwolfAmqpProducer).isPresent();
            assertThat(springwolfAmqpController).isPresent();
        }
    }

    @SpringJUnitConfig(
            classes = {
                SpringwolfAmqpAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.amqp.publishing.enabled=false"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                AsyncApiService.class,
                RabbitTemplate.class
            })
    @Nested
    class AmqpProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfAmqpProducer> springwolfAmqpProducer;

        @Autowired
        private Optional<SpringwolfAmqpController> springwolfAmqpController;

        @Test
        void springwolfAmqpProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfAmqpProducer).isNotPresent();
            assertThat(springwolfAmqpController).isNotPresent();
        }
    }
}
