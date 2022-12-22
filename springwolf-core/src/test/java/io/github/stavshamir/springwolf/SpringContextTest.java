package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultChannelsService;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Nested
public class SpringContextTest {

    @SpringBootTest
    @ContextConfiguration(classes = {
            CustomBeanAsyncApiDocketConfiguration.class, // user has defined an own AsyncApiDocket bean
            DefaultAsyncApiDocketService.class,
            DefaultAsyncApiService.class,
            DefaultChannelsService.class,
            DefaultSchemasService.class,
            DefaultAsyncApiService.class,
            DefaultAsyncApiSerializerService.class,
    })
    public static class AsyncApiDocketTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        public void testContextWithAsyncApiDocketBean() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }
    }

    @SpringBootTest
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
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
            "springwolf.docket.servers.test-protocol.protocol=test",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
    })
    public static class ApplicationPropertiesConfigurationTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        public void testContextWithApplicationProperties() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }
    }
}