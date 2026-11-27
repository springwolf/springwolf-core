// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureRestTestClient
class ApiIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Value("${spring.rabbitmq.host}")
    public String amqpHost;

    @Value("${spring.rabbitmq.port}")
    public String amqpPort;

    @Test
    void asyncApiResourceJsonArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = restTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        if (actual == null) {
            throw new IllegalStateException("Expected AsyncAPI response body");
        }
        String actualPatched = actual.replace(amqpHost + ":" + amqpPort, "amqp:5672");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actualPatched).isEqualTo(expected);
    }

    @Test
    void asyncApiResourceYamlArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = restTestClient
                .get()
                .uri(url)
                .accept(MediaType.APPLICATION_YAML)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        if (actual == null) {
            throw new IllegalStateException("Expected AsyncAPI response body");
        }
        String actualPatched = actual.replace(amqpHost + ":" + amqpPort, "amqp:5672");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.yaml"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.yaml")) {
            assert s != null;
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim() + "\n";
        }

        assertThat(actualPatched).isEqualTo(expected);
    }
}
