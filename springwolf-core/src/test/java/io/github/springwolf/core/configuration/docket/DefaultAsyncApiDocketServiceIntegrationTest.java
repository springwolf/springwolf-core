// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@Nested
@SpringJUnitConfig(
        classes = {
            DefaultAsyncApiDocketService.class,
        })
@EnableConfigurationProperties(SpringwolfConfigProperties.class)
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.extension-fields.x-api-name=api-name",
            "springwolf.docket.base-package=does.not.matter",
        })
class DefaultAsyncApiDocketServiceIntegrationTest {
    @Autowired
    private DefaultAsyncApiDocketService asyncApiDocketService;

    @Test
    void docketContentShouldBeLoadedFromProperties() {
        AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
        assertThat(docket).isNotNull();
        assertThat(docket.getInfo().getTitle()).isEqualTo("Info title was loaded from spring properties");
        assertThat(docket.getInfo().getExtensionFields().get("x-api-name")).isEqualTo("api-name");
    }
}
