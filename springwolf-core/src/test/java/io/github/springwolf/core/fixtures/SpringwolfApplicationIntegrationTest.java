// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.fixtures;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@SpringwolfIntegrationTest
@TestPropertySource(
        properties = {
            "springwolf.docket.base-package=does.not.matter",
            "springwolf.docket.info.title=Info title was loaded from integration test annotation",
            "springwolf.docket.info.version=1.1.1",
            "springwolf.docket.id=urn:io:github:springwolf:core:fixture",
            "springwolf.docket.default-content-type=application/json"
        })
@ExtendWith(SpringExtension.class)
public @interface SpringwolfApplicationIntegrationTest {}
