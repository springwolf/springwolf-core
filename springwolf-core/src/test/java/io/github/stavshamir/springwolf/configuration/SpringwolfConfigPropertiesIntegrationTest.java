// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SpringwolfConfigPropertiesIntegrationTest {

    @Nested
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.info.extension-fields.x-api-name=api-name",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
            })
    static class TestSimplePropertiesIntegrationTest {

        @Autowired
        private SpringwolfConfigProperties properties;

        @Test
        void enabledTest() {
            assertThat(properties.isEnabled()).isTrue();
        }

        @Test
        void docketInfoTest() {
            assertThat(properties.getDocket()).isNotNull();
            assertThat(properties.getDocket().getInfo().getTitle())
                    .isEqualTo("Info title was loaded from spring properties");
            assertThat(properties.getDocket().getInfo().getExtensionFields().get("x-api-name"))
                    .isEqualTo("api-name");
        }

        @Test
        void docketBasePackageTest() {
            assertThat(properties.getDocket().getBasePackage()).isEqualTo("io.github.stavshamir.springwolf.example");
        }

        @Test
        void docketServersTest() {
            assertThat(properties.getDocket().getServers())
                    .isEqualTo(Map.of(
                            "test-protocol",
                            Server.builder()
                                    .protocol("test")
                                    .url("some-server:1234")
                                    .build()));
        }
    }

    @Nested
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.info.extension-fields.x-api-name=api-name",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
            })
    static class PayloadWithoutCustomizingIntegrationTest {

        @Autowired
        private SpringwolfConfigProperties properties;

        @Test
        void payloadTest() {
            Map<String, Integer> actual = properties.getPayload().getExtractableClasses();

            // default values
            assertThat(actual).hasSize(6);
            assertThat(actual).containsEntry("org.apache.kafka.streams.kstream.KStream", 1);
            assertThat(actual).containsEntry("org.springframework.messaging.Message", 0);
            assertThat(actual).containsEntry("java.util.function.Consumer", 0);
            assertThat(actual).containsEntry("java.util.function.Supplier", 0);
            assertThat(actual).containsEntry("java.util.List", 0);
        }
    }

    @Nested
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.info.extension-fields.x-api-name=api-name",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.payload.extractable-classes.my.custom.class=1"
            })
    static class PayloadWithCustomizingIntegrationTest {

        @Autowired
        private SpringwolfConfigProperties properties;

        @Test
        void payloadCustomizedTest() {
            Map<String, Integer> actual = properties.getPayload().getExtractableClasses();

            // default values
            assertThat(actual).hasSize(7);
            assertThat(actual).containsEntry("my.custom.class", 1);
            // default values
            assertThat(actual).containsEntry("org.apache.kafka.streams.kstream.KStream", 1);
            assertThat(actual).containsEntry("org.springframework.messaging.Message", 0);
            assertThat(actual).containsEntry("java.util.function.Consumer", 0);
            assertThat(actual).containsEntry("java.util.function.Supplier", 0);
            assertThat(actual).containsEntry("java.util.List", 0);
            assertThat(actual).containsEntry("java.util.Optional", 0);
        }
    }

    @Nested
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.info.extension-fields.x-api-name=api-name",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234",
                "springwolf.payload.extractable-classes.java.util.List=-1"
            })
    static class PayloadDisabledIntegrationTest {

        @Autowired
        private SpringwolfConfigProperties properties;

        @Test
        void payloadDisabledTest() {
            Map<String, Integer> actual = properties.getPayload().getExtractableClasses();

            assertThat(actual).hasSize(5);
            // default values
            assertThat(actual).containsEntry("org.apache.kafka.streams.kstream.KStream", 1);
            assertThat(actual).containsEntry("org.springframework.messaging.Message", 0);
            assertThat(actual).containsEntry("java.util.function.Consumer", 0);
            assertThat(actual).containsEntry("java.util.function.Supplier", 0);
            assertThat(actual).containsEntry("java.util.Optional", 0);
        }
    }
}
