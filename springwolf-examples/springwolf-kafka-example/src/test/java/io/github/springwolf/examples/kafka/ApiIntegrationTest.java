// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
class ApiIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = Objects.requireNonNull(
                restTestClient
                        .get()
                        .uri(url)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody(),
                "Expected AsyncAPI response body");
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void asyncApiResourceArtifactYamlTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = Objects.requireNonNull(
                restTestClient
                        .get()
                        .uri(url)
                        .accept(MediaType.APPLICATION_YAML)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody(),
                "Expected AsyncAPI response body");
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void asyncApiResourceForVehicleGroupArtifactTest() throws Exception {
        String url = "/springwolf/docs/Only Vehicles";
        String actual = Objects.requireNonNull(
                restTestClient
                        .get()
                        .uri(url)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody(),
                "Expected AsyncAPI response body");
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "groups", "vehicles.actual.json"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/groups/vehicles.json")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void uiConfigTest() throws Exception {
        String url = "/springwolf/ui-config";
        String actual = Objects.requireNonNull(
                restTestClient
                        .get()
                        .uri(url)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody(),
                "Expected UI config response body");
        Files.writeString(Path.of("src", "test", "resources", "ui-config.actual.json"), actual);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/ui-config.json")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actual).isEqualTo(expected);
    }
}
