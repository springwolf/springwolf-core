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
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.id=urn:io:github:springwolf:example",
            "springwolf.docket.default-content-type=application/json",
            "springwolf.docket.base-package=io.github.springwolf.core.integrationtests.application.basic",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.host=some-server:1234",
        })
public @interface MinimalIntegrationTestContextConfiguration {}
