// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void asyncApiResourceArtifactYamlTest() throws Exception {
        String url = "/springwolf/docs";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_YAML));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String actual =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void asyncApiResourceForVehicleGroupArtifactTest() throws Exception {
        String url = "/springwolf/docs/Only Vehicles";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092").trim();
        Files.writeString(Path.of("src", "test", "resources", "groups", "vehicles.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/groups/vehicles.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void uiConfigTest() throws Exception {
        String url = "/springwolf/ui-config";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "ui-config.actual.json"), actual);

        InputStream s = this.getClass().getResourceAsStream("/ui-config.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actual).isEqualTo(expected);
    }
}
