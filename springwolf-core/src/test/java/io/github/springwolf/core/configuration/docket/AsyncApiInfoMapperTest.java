// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.docket;

import io.github.springwolf.asyncapi.v3.model.info.Contact;
import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.asyncapi.v3.model.info.License;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AsyncApiInfoMapperTest {

    @Test
    void shouldMapInfo() {
        // given
        SpringwolfConfigProperties.ConfigDocket.Info configDocketInfo =
                new SpringwolfConfigProperties.ConfigDocket.Info();
        configDocketInfo.setVersion("version");
        configDocketInfo.setTitle("title");
        configDocketInfo.setDescription("description");
        configDocketInfo.setTermsOfService("termsOfService");
        configDocketInfo.setContact(
                Contact.builder().name("name").url("url").email("email").build());
        configDocketInfo.setLicense(License.builder().name("name").url("url").build());
        configDocketInfo.setExtensionFields(Map.of("field", "value"));

        // when
        Info actual = AsyncApiInfoMapper.mapInfo(configDocketInfo);

        // then
        Info expected = Info.builder()
                .version("version")
                .title("title")
                .description("description")
                .termsOfService("termsOfService")
                .contact(
                        Contact.builder().name("name").url("url").email("email").build())
                .license(License.builder().name("name").url("url").build())
                .build();
        expected.setExtensionFields(Map.of("field", "value"));
        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class MergeInfo {

        @Test
        void shouldMergeInfo() {
            // given
            Info original = Info.builder()
                    .version("version")
                    .title("title")
                    .description("description")
                    .termsOfService("termsOfService")
                    .contact(Contact.builder()
                            .name("name")
                            .url("url")
                            .email("email")
                            .build())
                    .license(License.builder().name("name").url("url").build())
                    .build();

            Info updates = Info.builder()
                    .version("version2")
                    .title("title2")
                    .description("description2")
                    .termsOfService("termsOfService2")
                    .contact(Contact.builder()
                            .name("name2")
                            .url("url2")
                            .email("email2")
                            .build())
                    .license(License.builder().name("name2").url("url2").build())
                    .build();

            // when
            Info actual = AsyncApiInfoMapper.mergeInfo(original, updates);

            // then
            assertThat(actual)
                    .isEqualTo(Info.builder()
                            .version("version2")
                            .title("title2")
                            .description("description2")
                            .termsOfService("termsOfService2")
                            .contact(Contact.builder()
                                    .name("name2")
                                    .url("url2")
                                    .email("email2")
                                    .build())
                            .license(License.builder().name("name2").url("url2").build())
                            .build());
        }

        @Test
        void shouldKeepOriginalInfo() {
            // given
            Info original = Info.builder()
                    .version("version")
                    .title("title")
                    .description("description")
                    .termsOfService("termsOfService")
                    .contact(Contact.builder()
                            .name("name")
                            .url("url")
                            .email("email")
                            .build())
                    .license(License.builder().name("name").url("url").build())
                    .build();

            Info updates = Info.builder().build();

            // when
            Info actual = AsyncApiInfoMapper.mergeInfo(original, updates);

            // then
            assertThat(actual).isEqualTo(original);
        }

        @Test
        void shouldUpdateNestedFields() {
            // given
            Info original = Info.builder()
                    .contact(Contact.builder()
                            .name("name")
                            .url("url")
                            .email("email")
                            .build())
                    .license(License.builder().name("name").url("url").build())
                    .build();

            Info updates = Info.builder()
                    .contact(Contact.builder().name("name2").build())
                    .license(License.builder().url("url2").build())
                    .build();

            // when
            Info actual = AsyncApiInfoMapper.mergeInfo(original, updates);

            // then
            assertThat(actual)
                    .isEqualTo(Info.builder()
                            .contact(Contact.builder()
                                    .name("name2")
                                    .url("url")
                                    .email("email")
                                    .build())
                            .license(License.builder().name("name").url("url2").build())
                            .build());
        }
    }
}
