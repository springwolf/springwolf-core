// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.fixtures;

import io.github.stavshamir.springwolf.SpringwolfAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@ContextConfiguration(classes = {SpringwolfAutoConfiguration.class, ObjectMapperTestConfiguration.class})
@EnableConfigurationProperties
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.id=urn:io:github:stavshamir:springwolf:example",
            "springwolf.docket.default-content-type=application/json",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.integrationtests.application.basic",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.host=some-server:1234",
        })
public @interface MinimalIntegrationTestContextConfiguration {}
