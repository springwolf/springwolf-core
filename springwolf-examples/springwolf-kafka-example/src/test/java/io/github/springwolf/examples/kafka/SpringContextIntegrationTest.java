// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import io.github.springwolf.core.asyncapi.AsyncApiService;
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
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=kafka",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
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
            assertThat(asyncApiService.getAsyncAPI().getChannels().keySet())
                    .containsExactlyInAnyOrder(
                            "another-topic_id",
                            "avro-topic_id",
                            "example-topic_id",
                            "integer-topic_id",
                            "multi-payload-topic_id",
                            "no-payload-used-topic_id",
                            "protobuf-topic_id",
                            "string-topic_id",
                            "topic-defined-via-asyncPublisher-annotation_id",
                            "vehicle-topic_id",
                            "xml-topic_id",
                            "yaml-topic_id");
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
