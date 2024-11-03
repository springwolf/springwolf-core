// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actualPatched);
    }

    @Test
    void asyncApiResourceArtifactYamlTest() throws IOException {
        String url = "/springwolf/docs.yaml";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actualPatched);
    }

    @Test
    void asyncApiResourceForVehicleGroupArtifactTest() throws IOException {
        String url = "/springwolf/docs/Only Vehicles";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "groups", "vehicles.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/groups/vehicles.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actualPatched);
    }

    @Test
    void uiConfigTest() throws IOException {
        String url = "/springwolf/ui-config";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "ui-config.actual.json"), actual);

        InputStream s = this.getClass().getResourceAsStream("/ui-config.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actual);
    }
}
