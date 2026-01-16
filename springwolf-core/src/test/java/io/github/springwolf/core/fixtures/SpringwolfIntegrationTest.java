// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import io.github.springwolf.core.configuration.SpringwolfAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@ContextConfiguration(classes = {SpringwolfAutoConfiguration.class, JsonMapperTestConfiguration.class})
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "spring.application.name=springwolf-core-integration-test",
            "springwolf.enabled=true",
        })
public @interface SpringwolfIntegrationTest {}
