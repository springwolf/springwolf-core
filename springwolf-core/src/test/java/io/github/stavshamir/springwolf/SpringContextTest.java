package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultChannelsService;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SpringContextTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(classes = {
            CustomBeanAsyncApiDocketConfiguration.class, // user has defined an own AsyncApiDocket bean
            DefaultAsyncApiDocketService.class,
            DefaultAsyncApiService.class,
            DefaultChannelsService.class,
            DefaultSchemasService.class,
            DefaultAsyncApiService.class,
            DefaultAsyncApiSerializerService.class,
    })
    class AsyncApiDocketTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void testContextWithAsyncApiDocketBean() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }
    }

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(classes = {
            DefaultAsyncApiDocketService.class,
            DefaultAsyncApiService.class,
            DefaultChannelsService.class,
            DefaultSchemasService.class,
            DefaultAsyncApiService.class,
            DefaultAsyncApiSerializerService.class,
    })
    @EnableConfigurationProperties(value = {
            SpringWolfConfigProperties.class,
    })
    @TestPropertySource(properties = {
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.id=urn:io:github:stavshamir:springwolf:example",
            "springwolf.docket.default-content-type=application/yaml",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
    })
    class ApplicationPropertiesConfigurationTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void testContextWithApplicationProperties() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
            assertThat(asyncApiService.getAsyncAPI().getInfo().getTitle()).isEqualTo("Info title was loaded from spring properties");
            assertThat(asyncApiService.getAsyncAPI().getDefaultContentType()).isEqualTo("application/yaml");
            assertThat(asyncApiService.getAsyncAPI().getId()).isEqualTo("urn:io:github:stavshamir:springwolf:example");
        }
    }
}