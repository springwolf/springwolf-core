package io.github.stavshamir.springwolf.example.kafka;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.example.kafka.consumers.ExampleConsumer;
import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerIntegrationWithDockerIntegrationTest {

    @Autowired
    SpringwolfKafkaProducer springwolfKafkaProducer;

    @SpyBean
    ExampleConsumer exampleConsumer;

    @Autowired
    SpringwolfKafkaConfigProperties properties;

    @Container
    public static DockerComposeContainer<?> environment =
            new DockerComposeContainer<>(new File("docker-compose.yml")).withServices("kafka");

    @Test
    @Order(1)
    void verifyKafkaIsAvailable() {
        Map<String, Object> consumerProperties =
                properties.getPublishing().getProducer().buildProperties();
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
}
