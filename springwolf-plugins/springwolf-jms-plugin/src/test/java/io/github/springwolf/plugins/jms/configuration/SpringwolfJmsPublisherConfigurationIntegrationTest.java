// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.messages.MessagesService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.jms.controller.SpringwolfJmsController;
import io.github.springwolf.plugins.jms.publish.SpringwolfJmsPublisher;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringValueResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfJmsPublisherConfigurationIntegrationTest {

    @SpringJUnitConfig(
            classes = {
                SpringwolfJmsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.jms",
                "springwolf.plugin.jms.publishing.enabled=true"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                MessagesService.class,
                SchemasService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                JmsTemplate.class
            })
    @Nested
    class JmsPublisherWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfJmsPublisher> springwolfJmsPublisher;

        @Autowired
        private Optional<SpringwolfJmsController> springwolfJmsController;

        @Test
        void springwolfJmsPublisherShouldBePresentInSpringContext() {
            assertThat(springwolfJmsPublisher).isPresent();
            assertThat(springwolfJmsController).isPresent();
        }
    }

    @SpringJUnitConfig(
            classes = {
                SpringwolfJmsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.jms",
                "springwolf.plugin.jms.publishing.enabled=false"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                MessagesService.class,
                SchemasService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                JmsTemplate.class
            })
    @Nested
    class JmsPublisherWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfJmsPublisher> springwolfJmsPublisher;

        @Autowired
        private Optional<SpringwolfJmsController> springwolfJmsController;

        @Test
        void springwolfJmsProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfJmsPublisher).isNotPresent();
            assertThat(springwolfJmsController).isNotPresent();
        }
    }
}
