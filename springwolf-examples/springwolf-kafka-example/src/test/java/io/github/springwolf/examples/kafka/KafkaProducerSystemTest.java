// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto.ExampleEnum.FOO1;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real kafka instance.
 */
@Testcontainers
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class KafkaProducerSystemTest {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String APP_NAME = "app";
    private static final int APP_PORT = 8080;
    private static final String KAFKA_NAME = "kafka";
    private static final String topic = "example-topic";

    private static final boolean USE_SCHEMA_REGISTRY = false;

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withCopyFilesInContainer(".env") // do not copy all files in the directory
            .withServices(APP_NAME, KAFKA_NAME, USE_SCHEMA_REGISTRY ? "kafka-schema-registry" : "")
            .withExposedService(APP_NAME, APP_PORT)
            .waitingFor(APP_NAME, Wait.forLogMessage(".*AsyncAPI document was built.*", 1))
            .waitingFor(APP_NAME, Wait.forLogMessage(".*partitions assigned.*" + topic + ".*", 1))
            .withLogConsumer(APP_NAME, l -> Arrays.stream(
                            l.getUtf8StringWithoutLineEnding().split("(\n|\r\n)"))
                    .forEach(m -> log.debug("APP: {}", m)))
            .waitingFor(KAFKA_NAME, Wait.forLogMessage(".*Kafka Server started.*", 1))
            .withLogConsumer(KAFKA_NAME, l -> Arrays.stream(
                            l.getUtf8StringWithoutLineEnding().split("(\n|\r\n)"))
                    .forEach(m -> log.debug("KAFKA: {}", m)));

    private String baseUrl() {
        String host = environment.getServiceHost(APP_NAME, APP_PORT);
        int port = environment.getServicePort(APP_NAME, APP_PORT);
        return String.format("http://%s:%d", host, port);
    }

    @Test
    void producerCanUseSpringwolfConfigurationToSendMessage() throws JsonProcessingException {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("kafka_offset", List.of("0"));
        headers.put("kafka_receivedMessageKey", List.of("string"));

        ExamplePayloadDto payload = new ExamplePayloadDto("foo", 5, FOO1);
        String payloadAsString = new ObjectMapper().writeValueAsString(payload).replaceAll("\"", "\\\\\"");
        String message = "{\n" //
                + "    \"bindings\": {},\n"
                + "    \"headers\": {" //
                + "        \"kafka_offset\": 0,\n" //
                + "        \"kafka_receivedMessageKey\": \"string\"\n" //
                + "    },\n"
                + "    \"payloadType\": \"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto\",\n"
                + "    \"payload\": \"" + payloadAsString + "\"\n"
                + "}";

        String url = baseUrl() + "/springwolf/kafka/publish?topic=" + topic;
        HttpEntity<String> request = new HttpEntity<>(message, headers);

        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            // when
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // then
            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(environment.getContainerByServiceName(APP_NAME).get().getLogs())
                    .contains("Received new message in " + topic + ": " + payload);
        });
    }

    @Test
    @Disabled("Publishing AVRO is not supported")
    @DisabledIf(
            value = "withoutSchemaRegistry",
            disabledReason = "because it requires a running kafka-schema-registry instance (docker image= ~1GB).")
    void producerCanUseSpringwolfConfigurationToSendAvroMessage() throws JsonProcessingException {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        String payloadAsString =
                "{\"someEnum\": \"FOO1\", \"ExamplePayloadAvroDto\": {\"someString\": \"string\", \"someLong\": 0}}";
        String message = "{\n" //
                + "    \"bindings\": {},\n"
                + "    \"headers\": {},\n"
                + "    \"payloadType\": \"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto\",\n"
                + "    \"payload\": \"" + payloadAsString.replaceAll("\"", "\\\\\"") + "\"\n"
                + "}";

        String topic = "avro-topic";
        String url = baseUrl() + "/springwolf/kafka/publish?topic=" + topic;
        HttpEntity<String> request = new HttpEntity<>(message, headers);

        Awaitility.await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            // when
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // then
            assertThat(response.getStatusCode().value()).isEqualTo(200);
            assertThat(environment.getContainerByServiceName(APP_NAME).get().getLogs())
                    .contains("Received new message in " + topic + ": " + payloadAsString);
        });
    }

    boolean withoutSchemaRegistry() {
        return !USE_SCHEMA_REGISTRY;
    }
}
