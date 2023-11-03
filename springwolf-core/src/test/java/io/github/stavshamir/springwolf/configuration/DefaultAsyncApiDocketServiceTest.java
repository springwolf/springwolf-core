// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._6_0.model.info.Contact;
import com.asyncapi.v2._6_0.model.info.License;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties.ConfigDocket;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties.ConfigDocket.Info;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Maps.newHashMap;

class DefaultAsyncApiDocketServiceTest {

    @Test
    void testServiceShouldMapAllPropertiesToTheDocket() {
        // given
        ConfigDocket configDocket = new ConfigDocket();
        configDocket.setBasePackage("test-base-package");

        configDocket.setDefaultContentType("application/json");

        Server server =
                Server.builder().protocol("some-protocol").url("some-url").build();
        configDocket.setServers(newHashMap("some-protocol", server));

        Info info = new Info();
        info.setTitle("some-title");
        info.setVersion("some-version");
        info.setDescription("some-description");
        info.setLicense(new License("license-name", "license-url"));
        info.setContact(new Contact("contact-name", "contact-url", "contact-email"));
        info.setExtensionFields(Map.of("x-api-name", "api-name"));
        configDocket.setInfo(info);

        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setDocket(configDocket);

        AsyncApiDocketService docketService = new DefaultAsyncApiDocketService(Optional.empty(), properties);

        // when
        AsyncApiDocket asyncApiDocket = docketService.getAsyncApiDocket();

        // then
        assertThat(asyncApiDocket.getDefaultContentType()).isEqualTo(configDocket.getDefaultContentType());
        assertThat(asyncApiDocket.getServers()).isEqualTo(configDocket.getServers());
        assertThat(asyncApiDocket.getInfo().getTitle()).isEqualTo(info.getTitle());
        assertThat(asyncApiDocket.getInfo().getVersion()).isEqualTo(info.getVersion());
        assertThat(asyncApiDocket.getInfo().getDescription()).isEqualTo(info.getDescription());
        assertThat(asyncApiDocket.getInfo().getLicense()).isEqualTo(info.getLicense());
        assertThat(asyncApiDocket.getInfo().getContact()).isEqualTo(info.getContact());
        assertThat(asyncApiDocket.getInfo().getExtensionFields().get("x-api-name"))
                .isEqualTo("api-name");
    }

    @Test
    void docketServiceShouldDeliverCachedConfigDocketBean() {
        // repeatable invocations of AsyncApiDocketService.getAsyncApiDocket() should return the same docket instance
        // if a ConfigDocket @Bean is present.

        // given
        AsyncApiDocket customDocket = AsyncApiDocket.builder()
                .basePackage("test-base-package")
                .info(com.asyncapi.v2._6_0.model.info.Info.builder()
                        .title("some-title")
                        .version("some-version")
                        .build())
                .build();

        SpringwolfConfigProperties configProperties = new SpringwolfConfigProperties();

        AsyncApiDocketService docketService =
                new DefaultAsyncApiDocketService(Optional.of(customDocket), configProperties);

        // when
        AsyncApiDocket asyncApiDocket = docketService.getAsyncApiDocket();

        // then
        // second invocation should again return same instance
        assertThat(docketService.getAsyncApiDocket()).isSameAs(asyncApiDocket);
    }

    @Test
    void docketServiceShouldDeliverCachedSpringPropertiesBasedDocket() {
        // repeatable invocations of AsyncApiDocketService.getAsyncApiDocket() should return the same docket instance
        // if docket is based on environment properties.

        // given
        ConfigDocket configDocket = new ConfigDocket();
        configDocket.setBasePackage("test-base-package");
        configDocket.setDefaultContentType("application/json");

        Info info = new Info();
        info.setTitle("some-title");
        info.setVersion("some-version");
        configDocket.setInfo(info);

        Server server =
                Server.builder().protocol("some-protocol").url("some-url").build();
        configDocket.setServers(newHashMap("some-protocol", server));

        SpringwolfConfigProperties configProperties = new SpringwolfConfigProperties();
        configProperties.setDocket(configDocket);

        AsyncApiDocketService docketService = new DefaultAsyncApiDocketService(Optional.empty(), configProperties);

        // when
        AsyncApiDocket asyncApiDocket = docketService.getAsyncApiDocket();

        // then
        // second invocation should again return same instance
        assertThat(docketService.getAsyncApiDocket()).isSameAs(asyncApiDocket);
    }
}
