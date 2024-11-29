// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.controller.PublishingPayloadCreator;
import io.github.springwolf.plugins.sns.controller.SpringwolfSnsController;
import io.github.springwolf.plugins.sns.producer.SpringwolfSnsProducer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StringValueResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfSnsProducerConfigurationIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                SpringwolfSnsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                ObjectMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.sns.publishing.enabled=true"
            })
    @Nested
    class SqsProducerWillBeCreatedIfEnabledTest extends MockBeanConfiguration {
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
                ObjectMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.plugin.sns.publishing.enabled=false"
            })
    @Nested
    class SqsProducerWillNotBeCreatedIfDisabledTest extends MockBeanConfiguration {
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

    /**
     * Introduced due to migration of spring boot 3.3 -> 3.4 and @MockBean deprecation
     *
     * feature request: https://github.com/spring-projects/spring-framework/issues/33925
     */
    class MockBeanConfiguration {
        @MockitoBean
        private SpringwolfClassScanner springwolfClassScanner;

        @MockitoBean
        private ComponentsService componentsService;

        @MockitoBean
        private HeaderClassExtractor headerClassExtractor;

        @MockitoBean
        private PayloadMethodParameterService payloadMethodParameterService;

        @MockitoBean
        private StringValueResolver stringValueResolver;

        @MockitoBean
        private SnsTemplate snsTemplate;
    }
}
