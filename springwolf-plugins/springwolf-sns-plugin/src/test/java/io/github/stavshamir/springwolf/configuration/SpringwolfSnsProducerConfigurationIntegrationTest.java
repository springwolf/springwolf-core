// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import io.github.stavshamir.springwolf.asyncapi.controller.PublishingPayloadCreator;
import io.github.stavshamir.springwolf.asyncapi.controller.SpringwolfSnsController;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.sns.SpringwolfSnsAutoConfiguration;
import io.github.stavshamir.springwolf.producer.SpringwolfSnsProducer;
import io.github.stavshamir.springwolf.schemas.SchemasService;
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

public class SpringwolfSnsProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfSnsAutoConfiguration.class,
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
                "springwolf.plugin.sns.publishing.enabled=true"
            })
    @MockBeans(
            value = {
                @MockBean(ComponentClassScanner.class),
                @MockBean(SchemasService.class),
                @MockBean(AsyncApiDocketService.class),
                @MockBean(AsyncApiService.class),
                @MockBean(SnsTemplate.class)
            })
    @Nested
    class SqsProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfSnsProducer> springwolfSqsProducer;

        @Autowired
        private Optional<SpringwolfSnsController> springwolfSqsController;

        @Test
        void springwolfSqsProducerShouldBePresentInSpringContext() {
            assertThat(springwolfSqsProducer).isPresent();
            assertThat(springwolfSqsController).isPresent();
        }
    }

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfSnsAutoConfiguration.class,
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
                "springwolf.plugin.sns.publishing.enabled=false"
            })
    @MockBeans(
            value = {
                @MockBean(ComponentClassScanner.class),
                @MockBean(SchemasService.class),
                @MockBean(ChannelsService.class),
                @MockBean(SnsTemplate.class)
            })
    @Nested
    class SqsProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfSnsProducer> springwolfSqsProducer;

        @Autowired
        private Optional<SpringwolfSnsController> springwolfSqsController;

        @Test
        void springwolfSqsProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfSqsProducer).isNotPresent();
            assertThat(springwolfSqsController).isNotPresent();
        }
    }
}
