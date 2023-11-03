// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.amqp.SpringwolfAmqpAutoConfiguration;
import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfAmqpController;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.producer.SpringwolfAmqpProducer;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfAmqpProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfAmqpAutoConfiguration.class,
                PublishingPayloadCreator.class,
                ObjectMapperTestConfiguration.class
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.plugin.amqp.publishing.enabled=true"
            })
    @MockBeans(
            value = {
                @MockBean(AsyncApiService.class),
                @MockBean(RabbitTemplate.class),
                @MockBean(ComponentClassScanner.class),
                @MockBean(SchemasService.class),
                @MockBean(AsyncApiDocketService.class)
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

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfAmqpAutoConfiguration.class,
                PublishingPayloadCreator.class,
                ObjectMapperTestConfiguration.class
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.plugin.amqp.publishing.enabled=false"
            })
    @MockBeans(
            value = {
                @MockBean(AsyncApiService.class),
                @MockBean(RabbitTemplate.class),
                @MockBean(ComponentClassScanner.class),
                @MockBean(SchemasService.class)
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
