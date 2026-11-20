// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.fixture;

import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import io.github.springwolf.plugins.jms.configuration.JsonMapperTestConfiguration;
import io.github.springwolf.plugins.jms.configuration.SpringwolfJmsAutoConfiguration;
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
            SpringwolfJmsAutoConfiguration.class
        })
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "spring.application.name=springwolf-jms-web-integration-test",
            "springwolf.enabled=true",
            "springwolf.docket.base-package=io.github.springwolf.plugins.jms.integrationtests.application.basic",
            "springwolf.plugin.jms.publishing.enabled=true"
        })
public @interface SpringwolfIntegrationTest {}
