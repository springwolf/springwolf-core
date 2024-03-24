// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void asyncApiJsonTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        try (InputStream resourceStream = this.getClass().getResourceAsStream("/asyncapi.json")) {
            assert resourceStream != null;
            String expected = new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8).trim();
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }

    @Test
    void asyncApiYamlTest() throws IOException {
        String url = "/springwolf/docs.yaml";
        String actual = restTemplate.getForObject(url, String.class).trim();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actual);

        try (InputStream resourceStream = this.getClass().getResourceAsStream("/asyncapi.yaml")) {
            assert resourceStream != null;
            String expected = new String(resourceStream.readAllBytes(), StandardCharsets.UTF_8).trim();
            Assertions.assertThat(actual).isEqualTo(expected);
        }
    }
}
