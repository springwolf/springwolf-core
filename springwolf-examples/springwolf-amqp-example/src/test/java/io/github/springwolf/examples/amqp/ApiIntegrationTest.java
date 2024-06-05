// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    void asyncApiResourceJsonArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertEquals(expected, actual);
    }

    @Test
    void asyncApiResourceYamlArtifactTest() throws IOException {
        String url = "/springwolf/docs.yaml";
        String actual = restTemplate
                .getForObject(url, String.class)
                .replaceAll("\r", "")
                .trim();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actual);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8)
                    .replaceAll("\r", "")
                    .trim();
        }

        assertEquals(expected, actual);
    }
}
