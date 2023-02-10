package io.github.stavshamir.springwolf.example;

import io.github.stavshamir.springwolf.SpringWolfKafkaConfigProperties;
import io.github.stavshamir.springwolf.example.consumers.ExampleService;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.producers.ExampleProducer;
import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real kafka instance.
 */
@SpringBootTest(classes = {SpringwolfExampleApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestPropertySource(properties = {
        "springwolf.plugin.kafka.publishing.producer.bootstrap-servers=localhost:29092"
})
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerIntegrationWithDockerTest {

    @Autowired
    SpringwolfKafkaProducer springwolfKafkaProducer;

    @MockBean
    ExampleService exampleService;

    @Autowired
    ExampleProducer exampleProducer;

    @Autowired
    SpringWolfKafkaConfigProperties properties;


    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withServices("kafka");


    @Test
    @Order(1)
    void verifyKafkaIsAvailable() throws ExecutionException, InterruptedException {
        Map<String, Object> consumerProperties = properties.getPublishing().getProducer().buildProperties();
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, consumerProperties.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        adminProperties.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, consumerProperties.get(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG));
        adminProperties.put(SaslConfigs.SASL_MECHANISM, consumerProperties.get(SaslConfigs.SASL_MECHANISM));
        adminProperties.put(SaslConfigs.SASL_JAAS_CONFIG, consumerProperties.get(SaslConfigs.SASL_JAAS_CONFIG));

        AdminClient adminClient = KafkaAdminClient.create(adminProperties);
        await().atMost(10, SECONDS)
                .untilAsserted(() -> assertThat(adminClient.listTopics().names().get()).contains("example-topic"));
    }

    @Test
    @Order(2)
    public void producerCanUseSpringwolfConfigurationToSendMessage() throws IOException, JSONException, InterruptedException {
        Map<String, String> headers = new HashMap<>();
        headers.put("header-key", "header-value");
        ExamplePayloadDto payload = new ExamplePayloadDto();
        payload.setSomeString("foo");
        payload.setSomeLong(5);
        payload.setSomeEnum(FOO1);

        springwolfKafkaProducer.send("example-topic",headers, payload);
        verify(exampleService, timeout(10000)).doSomething(payload);
    }
}
