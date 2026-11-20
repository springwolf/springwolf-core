// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.configuration;

import io.awspring.cloud.sns.core.SnsTemplate;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.classes.SpringwolfClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.controller.publish.PublishingPayloadCreator;
import io.github.springwolf.plugins.sns.controller.SpringwolfSnsController;
import io.github.springwolf.plugins.sns.publish.SpringwolfSnsPublisher;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.StringValueResolver;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfSnsPublisherConfigurationIntegrationTest {

    @SpringJUnitConfig(
            classes = {
                SpringwolfSnsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.plugin.sns.publishing.enabled=true"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                SnsTemplate.class
            })
    @Nested
    class SqsPublisherWillBeCreatedIfEnabledTest {
        @Autowired
        private Optional<SpringwolfSnsPublisher> springwolfSqsPublisher;

        @Autowired
        private Optional<SpringwolfSnsController> springwolfSqsController;

        @Test
        void springwolfSqsPublisherShouldBePresentInSpringContext() {
            assertThat(springwolfSqsPublisher).isPresent();
            assertThat(springwolfSqsController).isPresent();
        }
    }

    @SpringJUnitConfig(
            classes = {
                SpringwolfSnsAutoConfiguration.class,
                PublishingPayloadCreator.class,
                JsonMapperTestConfiguration.class,
            })
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.plugin.sns.publishing.enabled=false"
            })
    @MockitoBean(
            types = {
                SpringwolfClassScanner.class,
                ComponentsService.class,
                HeaderClassExtractor.class,
                PayloadMethodParameterService.class,
                StringValueResolver.class,
                SnsTemplate.class
            })
    @Nested
    class SqsPublisherWillNotBeCreatedIfDisabledTest {
        @Autowired
        private Optional<SpringwolfSnsPublisher> springwolfSqsPublisher;

        @Autowired
        private Optional<SpringwolfSnsController> springwolfSqsController;

        @Test
        void springwolfSqsProducerShouldNotBePresentInSpringContext() {
            assertThat(springwolfSqsPublisher).isNotPresent();
            assertThat(springwolfSqsController).isNotPresent();
        }
    }
}
