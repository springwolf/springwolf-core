// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.rabbitmq.host}")
    public String amqpHost;

    @Value("${spring.rabbitmq.port}")
    public String amqpPort;

    @Test
    void asyncApiResourceJsonArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        String actualPatched = actual.replace(amqpHost + ":" + amqpPort, "amqp:5672");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertEquals(expected, actualPatched);
    }

    @Test
    void asyncApiResourceYamlArtifactTest() throws IOException {
        String url = "/springwolf/docs.yaml";
        String actual = restTemplate.getForObject(url, String.class);
        String actualPatched = actual.replace(amqpHost + ":" + amqpPort, "amqp:5672");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim() + "\n";
        }

        assertEquals(expected, actualPatched);
    }
}
