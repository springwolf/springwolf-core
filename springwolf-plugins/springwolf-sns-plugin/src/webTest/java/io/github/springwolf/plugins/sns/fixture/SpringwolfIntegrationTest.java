// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.fixture;

import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import io.github.springwolf.plugins.sns.configuration.JsonMapperTestConfiguration;
import io.github.springwolf.plugins.sns.configuration.SpringwolfSnsAutoConfiguration;
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
            SpringwolfSnsAutoConfiguration.class
        })
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "spring.application.name=springwolf-sns-web-integration-test",
            "springwolf.enabled=true",
            "springwolf.docket.base-package=io.github.springwolf.plugins.sns.integrationtests.application.basic",
            "springwolf.plugin.sns.publishing.enabled=true"
        })
public @interface SpringwolfIntegrationTest {}
