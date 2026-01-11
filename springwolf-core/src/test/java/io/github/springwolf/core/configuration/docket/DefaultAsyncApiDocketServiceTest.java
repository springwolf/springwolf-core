// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.info.Contact;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties.ConfigDocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DefaultAsyncApiDocketServiceTest {

    @Test
    void serviceShouldMapAllPropertiesToTheDocket() {
        // given
        ConfigDocket configDocket = new ConfigDocket();
        configDocket.setBasePackage("test-base-package");

        configDocket.setDefaultContentType("application/json");

        ConfigDocket.Info info = new ConfigDocket.Info();
        info.setTitle("some-title");
        info.setVersion("some-version");
        info.setDescription("some-description");
        info.setTermsOfService("some-terms-of-service");
        info.setLicense(new License("license-name", "license-url"));
        info.setContact(new Contact("contact-name", "contact-url", "contact-email"));
        info.setExtensionFields(Map.of("x-api-name", "api-name"));
        configDocket.setInfo(info);

        SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
        properties.setDocket(configDocket);

        AsyncApiDocketService docketService =
                new DefaultAsyncApiDocketService(properties, mock(ApplicationContext.class));

        // when
        AsyncApiDocket asyncApiDocket = docketService.getAsyncApiDocket();

        // then
        assertThat(asyncApiDocket.getDefaultContentType()).isEqualTo(configDocket.getDefaultContentType());
        assertThat(asyncApiDocket.getServers()).isEqualTo(configDocket.getServers());
        assertThat(asyncApiDocket.getInfo().getTitle()).isEqualTo(info.getTitle());
        assertThat(asyncApiDocket.getInfo().getVersion()).isEqualTo(info.getVersion());
        assertThat(asyncApiDocket.getInfo().getDescription()).isEqualTo(info.getDescription());
        assertThat(asyncApiDocket.getInfo().getTermsOfService()).isEqualTo(info.getTermsOfService());
        assertThat(asyncApiDocket.getInfo().getLicense()).isEqualTo(info.getLicense());
        assertThat(asyncApiDocket.getInfo().getContact()).isEqualTo(info.getContact());
        assertThat(asyncApiDocket.getInfo().getExtensionFields().get("x-api-name"))
                .isEqualTo("api-name");
    }

    @Test
    void docketServiceShouldDeliverCachedDocket() {
        // repeatable invocations of AsyncApiDocketService.getAsyncApiDocket() should return the same docket instance
        // if docket is based on environment properties.

        // given
        ConfigDocket configDocket = new ConfigDocket();
        configDocket.setBasePackage("test-base-package");
        configDocket.setDefaultContentType("application/json");

        ConfigDocket.Info info = new ConfigDocket.Info();
        info.setTitle("some-title");
        info.setVersion("some-version");
        configDocket.setInfo(info);

        SpringwolfConfigProperties configProperties = new SpringwolfConfigProperties();
        configProperties.setDocket(configDocket);

        AsyncApiDocketService docketService =
                new DefaultAsyncApiDocketService(configProperties, mock(ApplicationContext.class));

        // when
        AsyncApiDocket asyncApiDocket = docketService.getAsyncApiDocket();

        // then
        // second invocation should again return same instance
        assertThat(docketService.getAsyncApiDocket()).isSameAs(asyncApiDocket);
    }

    @Nested
    class MissingProperties {

        private ConfigDocket validDocket;
        private AsyncApiDocketService docketService;
        private ApplicationContext applicationContext;

        @BeforeEach
        void setUp() {
            applicationContext = mock(ApplicationContext.class);
            when(applicationContext.getEnvironment()).thenReturn(new StandardEnvironment());

            validDocket = new ConfigDocket();
            validDocket.setBasePackage("test-base-package");

            ConfigDocket.Info info = new ConfigDocket.Info();
            info.setTitle("some-title");
            info.setVersion("some-version");
            validDocket.setInfo(info);

            SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
            properties.setDocket(validDocket);
            docketService = new DefaultAsyncApiDocketService(properties, applicationContext);
        }

        @ParameterizedTest
        @CsvSource(
                value = {"''", "null"},
                nullValues = {"null"})
        void missingBasePackageTest(String value) {
            // given
            validDocket.setBasePackage(value);

            // when
            assertThatThrownBy(docketService::getAsyncApiDocket)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("One or more required fields (docket, basePackage)");
        }

        @Test
        void missingBasePackageResolvesSpringBootApplicationPackage() {
            // given
            validDocket.setBasePackage(null);

            DefaultAsyncApiDocketServiceTest springBootAppClass = new DefaultAsyncApiDocketServiceTest();
            when(applicationContext.getBeansWithAnnotation(SpringBootApplication.class))
                    .thenReturn(Map.of("bean-name", springBootAppClass));

            // when
            AsyncApiDocket docket = docketService.getAsyncApiDocket();

            // then
            assertThat(docket.getBasePackage())
                    .isEqualTo(springBootAppClass.getClass().getPackage().getName());
        }

        @ParameterizedTest
        @CsvSource(
                value = {"''", "null"},
                nullValues = {"null"})
        void missingInfoTitle(String value) {
            // given
            validDocket.getInfo().setTitle(value);

            // when
            assertThatThrownBy(docketService::getAsyncApiDocket)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(
                            "One or more required fields of the info object (title, version) in application.properties with path prefix springwolf is not set.");
        }

        @Test
        void missingInfoTitleUsesApplicationNameFallback() {
            // given
            validDocket.getInfo().setTitle(null);
            ConfigurableEnvironment environment = new StandardEnvironment();
            environment
                    .getPropertySources()
                    .addFirst(new MapPropertySource(
                            "spring.application.name", Map.of("spring.application.name", "my-app-name")));
            when(applicationContext.getEnvironment()).thenReturn(environment);

            // when
            AsyncApiDocket docket = docketService.getAsyncApiDocket();

            // then
            assertThat(docket.getInfo().getTitle()).isEqualTo("my-app-name");
        }

        @ParameterizedTest
        @CsvSource(
                value = {"''", "null"},
                nullValues = {"null"})
        void missingInfoVersion(String value) {
            // given
            validDocket.getInfo().setVersion(value);

            // when
            assertThatThrownBy(docketService::getAsyncApiDocket)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(
                            "One or more required fields of the info object (title, version) in application.properties with path prefix springwolf is not set.");
        }

        @ParameterizedTest
        @CsvSource(
                value = {"''", "null"},
                nullValues = {"null"})
        void missingServerProtocol(String value) {
            // given
            validDocket.setServers(Map.of(
                    "some-protocol",
                    Server.builder().protocol(value).host("some-url").build()));

            // when
            assertThatThrownBy(docketService::getAsyncApiDocket)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(
                            "One or more required fields (protocol, host) of the server object (name=some-protocol) has not been defined in application.properties with path prefix springwolf");
        }

        @ParameterizedTest
        @CsvSource(
                value = {"''", "null"},
                nullValues = {"null"})
        void missingServerHost(String value) {
            // given
            validDocket.setServers(Map.of(
                    "some-protocol",
                    Server.builder().protocol("some-protocol").host(value).build()));

            // when
            assertThatThrownBy(docketService::getAsyncApiDocket)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(
                            "One or more required fields (protocol, host) of the server object (name=some-protocol) has not been defined in application.properties with path prefix springwolf");
        }
    }
}
