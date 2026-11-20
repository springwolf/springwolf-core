// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.messages.MessagesService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.kafka.controller.SpringwolfKafkaController;
import io.github.springwolf.plugins.kafka.publish.SpringwolfKafkaPublisher;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringValueResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfKafkaPublisherConfigurationIntegrationTest {

    @SpringJUnitConfig(
            classes = {
                SpringwolfKafkaAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.kafka",
                "springwolf.plugin.kafka.publishing.enabled=true"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                MessagesService.class,
                SchemasService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class
            })
    @Nested
    class KafkaPublisherWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfKafkaPublisher> springwolfKafkaPublisher;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldBePresentInSpringContext() {
            assertThat(springwolfKafkaPublisher).isPresent();
            assertThat(springwolfKafkaController).isPresent();
        }
    }

    @SpringJUnitConfig(
            classes = {
                SpringwolfKafkaAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.kafka",
                "springwolf.plugin.kafka.publishing.enabled=false"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                MessagesService.class,
                SchemasService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class
            })
    @Nested
    class KafkaPublisherWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfKafkaPublisher> springwolfKafkaPublisher;

        @Autowired
        private Optional<SpringwolfKafkaController> springwolfKafkaController;

        @Test
        void springwolfKafkaTemplateShouldNotBePresentInSpringContext() {
            assertThat(springwolfKafkaPublisher).isNotPresent();
            assertThat(springwolfKafkaController).isNotPresent();
        }
    }
}
