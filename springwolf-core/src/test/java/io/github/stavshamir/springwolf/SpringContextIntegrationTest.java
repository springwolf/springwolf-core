// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.fixtures.MinimalIntegrationTestContextConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@MinimalIntegrationTestContextConfiguration
public class SpringContextIntegrationTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private AsyncApiService asyncApiService;

    @Test
    void testContextWithApplicationProperties() {
        assertNotNull(context);

        assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        assertThat(asyncApiService.getAsyncAPI().getInfo().getTitle())
                .isEqualTo("Info title was loaded from spring properties");
        assertThat(asyncApiService.getAsyncAPI().getDefaultContentType()).isEqualTo("application/yaml");
        assertThat(asyncApiService.getAsyncAPI().getId()).isEqualTo("urn:io:github:stavshamir:springwolf:example");
    }
}
