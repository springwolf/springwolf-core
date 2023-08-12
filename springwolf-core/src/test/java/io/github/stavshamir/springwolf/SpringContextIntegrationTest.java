// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiSerializerService;
import io.github.stavshamir.springwolf.asyncapi.DefaultAsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.DefaultChannelsService;
import io.github.stavshamir.springwolf.configuration.DefaultAsyncApiDocketService;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import io.github.stavshamir.springwolf.fixtures.MinimalTestContextConfiguration;
import io.github.stavshamir.springwolf.schemas.DefaultSchemasService;
import io.github.stavshamir.springwolf.schemas.example.ExampleJsonGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringContextIntegrationTest {

    @ExtendWith(SpringExtension.class)
    @Nested
    @ContextConfiguration(
            classes = {
                SpringwolfConfigProperties.class,
                CustomBeanAsyncApiDocketConfiguration.class, // user has defined an own AsyncApiDocket bean
                DefaultAsyncApiDocketService.class,
                DefaultAsyncApiService.class,
                DefaultChannelsService.class,
                DefaultSchemasService.class,
                ExampleJsonGenerator.class,
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
            assertThat(asyncApiService.getAsyncAPI().getInfo().getTitle())
                    .isEqualTo("CustomBeanAsyncApiDocketConfiguration-title");
        }
    }

    @ExtendWith(SpringExtension.class)
    @Nested
    @MinimalTestContextConfiguration
    class ApplicationPropertiesConfigurationTest {

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
}
