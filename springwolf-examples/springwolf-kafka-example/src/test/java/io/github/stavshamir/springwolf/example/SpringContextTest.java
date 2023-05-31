package io.github.stavshamir.springwolf.example;

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


public class SpringContextTest {

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
    @Nested
    @DirtiesContext
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

        @Test
        void testAllChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(6);
        }
    }

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
    @Nested
    @DirtiesContext
    @TestPropertySource(properties = {
            "customAsyncApiDocketBean=false",
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
            // 2 channels defined in the AsyncDocket are not found,
            // however PRODUCER_TOPIC is also used in ExampleProducer (5 - 2 + 1 = 4)
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(4);
        }
    }

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
    @Nested
    @DirtiesContext
    @TestPropertySource(properties = {
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
