package io.github.stavshamir.springwolf.configuration;


import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.License;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.SpringWolfConfigProperties;
import io.github.stavshamir.springwolf.SpringWolfConfigProperties.ConfigDocket;
import io.github.stavshamir.springwolf.SpringWolfConfigProperties.ConfigDocket.Info;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.util.Maps.newHashMap;

public class DefaultAsyncApiDocketServiceTest {

    @Test
    public void testConfigurationShouldMapAllPropertiesToTheDocket() {
        // given
        ConfigDocket configDocket = new ConfigDocket();
        configDocket.setBasePackage("test-base-package");

        Server server = Server.builder()
                .protocol("some-protocol")
                .url("some-url")
                .build();
        configDocket.setServers(newHashMap("some-protocol", server));

        Info info = new Info();
        info.setTitle("some-title");
        info.setVersion("some-version");
        info.setDescription("some-description");
        info.setLicense(new License("license-name", "license-url"));
        info.setContact(new Contact("contact-name", "contact-url", "contact-email"));
        configDocket.setInfo(info);


        SpringWolfConfigProperties properties = new SpringWolfConfigProperties();
        properties.setDocket(configDocket);

        // when
        DefaultAsyncApiDocketService docketConfiguration = new DefaultAsyncApiDocketService(Optional.empty(), Optional.of(properties));
        AsyncApiDocket asyncApiDocket = docketConfiguration.getAsyncApiDocket();

        // then
        assertThat(asyncApiDocket.getServers()).isEqualTo(configDocket.getServers());
        assertThat(asyncApiDocket.getInfo().getTitle()).isEqualTo(info.getTitle());
        assertThat(asyncApiDocket.getInfo().getVersion()).isEqualTo(info.getVersion());
        assertThat(asyncApiDocket.getInfo().getDescription()).isEqualTo(info.getDescription());
        assertThat(asyncApiDocket.getInfo().getLicense()).isEqualTo(info.getLicense());
        assertThat(asyncApiDocket.getInfo().getContact()).isEqualTo(info.getContact());
    }

    @Test
    public void testNoConfigurationShouldThrowException() {
        assertThatThrownBy(() -> {
            DefaultAsyncApiDocketService docketConfiguration = new DefaultAsyncApiDocketService(Optional.empty(), Optional.empty());
            docketConfiguration.getAsyncApiDocket();
        })
                .isInstanceOf(IllegalArgumentException.class);
    }
}
