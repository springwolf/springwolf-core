// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfKafkaController;
import io.github.stavshamir.springwolf.asyncapi.kafka.SpringwolfKafkaAutoConfiguration;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import io.github.stavshamir.springwolf.schemas.ComponentsService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfKafkaProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                SpringwolfKafkaAutoConfiguration.class,
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
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.kafka.publishing.enabled=true"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadClassExtractor.class),
                @MockBean(AsyncApiDocketService.class)
            })
    class KafkaProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldBePresentInSpringContext() {
            assertThat(springwolfKafkaProducer).isPresent();
            assertThat(springwolfKafkaController).isPresent();
        }
    }

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                SpringwolfKafkaAutoConfiguration.class,
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
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.kafka.publishing.enabled=false"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadClassExtractor.class)
            })
    class KafkaProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfKafkaProducer> springwolfKafkaProducer;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldNotBePresentInSpringContext() {
            assertThat(springwolfKafkaProducer).isNotPresent();
            assertThat(springwolfKafkaController).isNotPresent();
        }
    }
}
