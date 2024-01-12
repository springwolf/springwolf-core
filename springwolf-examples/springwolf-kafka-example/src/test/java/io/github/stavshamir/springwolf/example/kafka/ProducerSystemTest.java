// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.example.kafka.consumers.AvroConsumer;
import io.github.stavshamir.springwolf.example.kafka.consumers.ExampleConsumer;
import io.github.stavshamir.springwolf.example.kafka.dto.avro.ExampleEnum;
import io.github.stavshamir.springwolf.example.kafka.dto.avro.ExamplePayloadAvroDto;
import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real kafka instance.
 */
@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {"spring.kafka.bootstrap-servers=localhost:9092"})
@TestMethodOrder(OrderAnnotation.class)
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerSystemTest {

    @Autowired
    SpringwolfKafkaProducer springwolfKafkaProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @SpyBean
    AvroConsumer avroConsumer;

    @Autowired
    SpringwolfKafkaConfigProperties properties;

    @Container
    public static DockerComposeContainer<?> environment =
            new DockerComposeContainer<>(new File("docker-compose.yml")).withServices("kafka");

    @Test
    @Order(1)
    void verifyKafkaIsAvailable() {
        Map<String, Object> consumerProperties =
                properties.getPublishing().getProducer().buildProperties(new DefaultSslBundleRegistry());
        AdminClient adminClient = KafkaAdminClient.create(consumerProperties);
        await().atMost(60, SECONDS)
                .untilAsserted(
                        () -> assertThat(adminClient.listTopics().names().get()).contains("example-topic"));
    }

    @Test
    @Order(2)
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        Map<String, String> headers = new HashMap<>();
        headers.put("header-key", "header-value");
        ExamplePayloadDto payload = new ExamplePayloadDto("foo", 5, FOO1);

        // when
        springwolfKafkaProducer.send("example-topic", "key", headers, payload);

        // then
        verify(exampleConsumer, timeout(10000)).receiveExamplePayload(payload);
    }

    @Test
    @Order(3)
    @Disabled("because it requires a running kafka-schema-registry instance (docker image= ~1GB).")
    void producerCanUseSpringwolfConfigurationToSendAvroMessage() {
        // given
        ExamplePayloadAvroDto payload = new ExamplePayloadAvroDto("foo", 5, ExampleEnum.FOO1);

        // when
        springwolfKafkaProducer.send("avro-topic", "key", Map.of(), payload);

        // then
        verify(avroConsumer, timeout(10000)).receiveExampleAvroPayload(payload);
    }
}
