// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.core.fixtures.MinimalIntegrationTestContextConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@MinimalIntegrationTestContextConfiguration
class SpringContextIntegrationTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private AsyncApiService asyncApiService;

    @Test
    void contextWithApplicationProperties() {
        assertThat(context).isNotNull();

        assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        assertThat(asyncApiService.getAsyncAPI().getInfo().getTitle())
                .isEqualTo("Info title was loaded from spring properties");
        assertThat(asyncApiService.getAsyncAPI().getDefaultContentType()).isEqualTo("application/json");
        assertThat(asyncApiService.getAsyncAPI().getId()).isEqualTo("urn:io:github:springwolf:example");
    }
}
