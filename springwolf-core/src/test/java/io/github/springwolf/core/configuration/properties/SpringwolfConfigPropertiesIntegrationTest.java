// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.properties;

import io.github.springwolf.asyncapi.v3.model.server.Server;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties.ConfigDocket.Group;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
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
                "springwolf.docket.base-package=io.github.springwolf.core.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
            })
    class TestSimplePropertiesIntegrationTest {

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
            assertThat(properties.getDocket().getBasePackage()).isEqualTo("io.github.springwolf.core.example");
        }

        @Test
        void docketServersTest() {
            assertThat(properties.getDocket().getServers())
                    .isEqualTo(Map.of(
                            "test-protocol",
                            Server.builder()
                                    .protocol("test")
                                    .host("some-server:1234")
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
                "springwolf.docket.base-package=io.github.springwolf.core.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
            })
    class PayloadWithoutCustomizingIntegrationTest {

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
                "springwolf.docket.base-package=io.github.springwolf.core.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.payload.extractable-classes.my.custom.class=1"
            })
    class PayloadWithCustomizingIntegrationTest {

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
                "springwolf.docket.base-package=io.github.springwolf.core.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
                "springwolf.payload.extractable-classes.java.util.List=-1"
            })
    class PayloadDisabledIntegrationTest {

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

    @Nested
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.docket.group-configs[0].group=SEND-GROUP",
                "springwolf.docket.group-configs[0].action-to-match=send",
                "springwolf.docket.group-configs[0].channel-name-to-match=/*,a*b",
                "springwolf.docket.group-configs[0].message-name-to-match=/*",
                "springwolf.docket.group-configs[1].group=RECEIVE-GROUP",
                "springwolf.docket.group-configs[1].action-to-match=",
                "springwolf.docket.group-configs[1].channel-name-to-match=",
                "springwolf.docket.group-configs[1].message-name-to-match="
            })
    class GroupConfigTest {

        @Autowired
        private SpringwolfConfigProperties properties;

        @Test
        void groupConfigIsMappedCorrectly() {
            // given
            Group sendGroup = new Group();
            sendGroup.setGroup("SEND-GROUP");
            sendGroup.setActionToMatch(List.of(Group.Action.SEND));
            sendGroup.setChannelNameToMatch(List.of("/*", "a*b"));
            sendGroup.setMessageNameToMatch(List.of("/*"));
            Group receiveGroup = new Group();
            receiveGroup.setGroup("RECEIVE-GROUP");

            // when
            List<Group> actual = properties.getDocket().getGroupConfigs();

            // then
            assertThat(actual).hasSize(2);
            assertThat(actual.get(0)).isEqualTo(sendGroup);
            assertThat(actual.get(1)).isEqualTo(receiveGroup);
        }
    }
}
