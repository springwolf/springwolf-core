// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.fixture;

import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import io.github.springwolf.plugins.sqs.configuration.JsonMapperTestConfiguration;
import io.github.springwolf.plugins.sqs.configuration.SpringwolfSqsAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@ContextConfiguration(
        classes = {
            SpringwolfAutoConfiguration.class,
            JsonMapperTestConfiguration.class,
            SpringwolfSqsAutoConfiguration.class
        })
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "spring.application.name=springwolf-sqs-web-integration-test",
            "springwolf.enabled=true",
            "springwolf.docket.base-package=io.github.springwolf.plugins.sqs.integrationtests.application.basic",
            "springwolf.plugin.sqs.publishing.enabled=true"
        })
public @interface SpringwolfIntegrationTest {}
