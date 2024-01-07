// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringContextIntegrationTest {

    @SpringBootTest(classes = SpringwolfKafkaExampleApplication.class)
    @EmbeddedKafka(
            partitions = 1,
            brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
    @Nested
    @DirtiesContext
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
                "springwolf.docket.servers.test-protocol.protocol=kafka",
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
        }

        @Test
        void testAllChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(7);
        }
    }

    @SpringBootTest(classes = SpringwolfKafkaExampleApplication.class)
    @EmbeddedKafka(
            partitions = 1,
            brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
    @Nested
    @DirtiesContext
    @TestPropertySource(
            properties = {
                "springwolf.scanner.async-listener.enabled=false",
                "springwolf.scanner.async-publisher.enabled=false",
                "springwolf.scanner.consumer-data.enabled=false",
                "springwolf.scanner.producer-data.enabled=false",
                "springwolf.plugin.kafka.scanner.kafka-listener.enabled=false"
            })
    class DisabledScannerTest {

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void testNoChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).isEmpty();
        }
    }
}
