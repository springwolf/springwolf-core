// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfSqsController;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.stavshamir.springwolf.asyncapi.sqs.SpringwolfSqsAutoConfiguration;
import io.github.stavshamir.springwolf.producer.SpringwolfSqsProducer;
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

public class SpringwolfSqsProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfSqsAutoConfiguration.class,
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
                "springwolf.plugin.sqs.publishing.enabled=true"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadClassExtractor.class),
                @MockBean(AsyncApiDocketService.class),
                @MockBean(AsyncApiService.class),
                @MockBean(SqsTemplate.class)
            })
    @Nested
    class SqsProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfSqsProducer> springwolfSqsProducer;

        @Autowired
        private Optional<SpringwolfSqsController> springwolfSqsController;

        @Test
        void springwolfSqsProducerShouldBePresentInSpringContext() {
            assertThat(springwolfSqsProducer).isPresent();
            assertThat(springwolfSqsController).isPresent();
        }
    }

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfSqsAutoConfiguration.class,
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
                "springwolf.plugin.sqs.publishing.enabled=false"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadClassExtractor.class),
                @MockBean(ChannelsService.class),
                @MockBean(SqsTemplate.class)
            })
    @Nested
    class SqsProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfSqsProducer> springwolfSqsProducer;

        @Autowired
        private Optional<SpringwolfSqsController> springwolfSqsController;

        @Test
        void springwolfSqsProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfSqsProducer).isNotPresent();
            assertThat(springwolfSqsController).isNotPresent();
        }
    }
}
