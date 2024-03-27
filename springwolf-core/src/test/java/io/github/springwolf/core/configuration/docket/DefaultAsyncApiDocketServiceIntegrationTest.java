// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Nested
@ContextConfiguration(
        classes = {
            DefaultAsyncApiDocketService.class,
        })
@EnableConfigurationProperties(SpringwolfConfigProperties.class)
@TestPropertySource(
        properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.info.extension-fields.x-api-name=api-name",
            "springwolf.docket.base-package=io.github.springwolf.core.example",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.host=some-server:1234"
        })
class DefaultAsyncApiDocketServiceIntegrationTest {
    @Autowired
    private DefaultAsyncApiDocketService asyncApiDocketService;

    @Test
    void testDocketContentShouldBeLoadedFromProperties() {
        AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
        assertThat(docket).isNotNull();
        assertThat(docket.getInfo().getTitle()).isEqualTo("Info title was loaded from spring properties");
        assertThat(docket.getInfo().getExtensionFields().get("x-api-name")).isEqualTo("api-name");
    }
}
