package io.github.stavshamir.springwolf.configuration;

import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2._6_0.model.server.Server;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultAsyncApiDocketServiceIntegrationTest {

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
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234"
            })
    class DocketWillBeAutomaticallyCreateIfNoCustomDocketIsPresentTest {

        @Autowired
        private DefaultAsyncApiDocketService asyncApiDocketService;

        @Test
        void testDocketContentShouldBeLoadedFromProperties() {
            AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();
            assertThat(docket).isNotNull();
            assertThat(docket.getInfo().getTitle()).isEqualTo("Info title was loaded from spring properties");
        }
    }

    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(
            classes = {
                DefaultAsyncApiDocketService.class,
            })
    @EnableConfigurationProperties(SpringwolfConfigProperties.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Docket was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=test",
                "springwolf.docket.servers.test-protocol.url=some-server:1234"
            })
    @Import(DocketWillNotBeAutomaticallyCreateIfCustomDocketIsPresentTest.CustomAsyncApiDocketConfiguration.class)
    @Nested
    class DocketWillNotBeAutomaticallyCreateIfCustomDocketIsPresentTest {

        @TestConfiguration
        public static class CustomAsyncApiDocketConfiguration {
            @Bean
            public AsyncApiDocket docket() {
                Info info = Info.builder()
                        .title("Custom docket was used")
                        .version("1.0.0")
                        .build();

                return AsyncApiDocket.builder()
                        .info(info)
                        .basePackage("package")
                        .server(
                                "kafka",
                                Server.builder()
                                        .protocol("kafka")
                                        .url("kafka:9092")
                                        .build())
                        .build();
            }
        }

        @Autowired
        private DefaultAsyncApiDocketService asyncApiDocketService;

        @Test
        void testDocketContentShouldNotBeLoadedFromProperties() {
            AsyncApiDocket docket = asyncApiDocketService.getAsyncApiDocket();

            assertThat(docket).isNotNull();
            assertThat(docket.getInfo().getTitle()).isEqualTo("Custom docket was used");
        }
    }
}
