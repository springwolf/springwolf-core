// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.configuration;

import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.jms.controller.SpringwolfJmsController;
import io.github.springwolf.plugins.jms.producer.SpringwolfJmsProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfJmsProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfJmsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                ObjectMapperTestConfiguration.class
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.jms",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.jms.publishing.enabled=true"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadMethodParameterService.class),
                @MockBean(HeaderClassExtractor.class),
                @MockBean(JmsTemplate.class)
            })
    @Nested
    class JmsProducerWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfJmsProducer> springwolfJmsProducer;

        @Autowired
        private Optional<SpringwolfJmsController> springwolfJmsController;

        @Test
        void springwolfJmsProducerShouldBePresentInSpringContext() {
            assertThat(springwolfJmsProducer).isPresent();
            assertThat(springwolfJmsController).isPresent();
        }
    }

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfJmsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                ObjectMapperTestConfiguration.class
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.plugins.jms",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.jms.publishing.enabled=false"
            })
    @MockBeans(
            value = {
                @MockBean(SpringwolfClassScanner.class),
                @MockBean(ComponentsService.class),
                @MockBean(PayloadMethodParameterService.class),
                @MockBean(HeaderClassExtractor.class),
                @MockBean(JmsTemplate.class)
            })
    @Nested
    class JmsProducerWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfJmsProducer> springwolfJmsProducer;

        @Autowired
        private Optional<SpringwolfJmsController> springwolfJmsController;

        @Test
        void springwolfJmsProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfJmsProducer).isNotPresent();
            assertThat(springwolfJmsController).isNotPresent();
        }
    }
}
