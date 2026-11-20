// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.controller;

import io.github.springwolf.web.fixtures.SpringwolfApplicationIntegrationTest;
import io.github.springwolf.web.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
            "springwolf.endpoint.actuator.enabled=true",
            "management.endpoints.web.exposure.include=springwolf"
        })
@SpringwolfApplicationIntegrationTest
public class ActuatorIntegrationTest {
    @LocalManagementPort
    private int managementPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void actuatorEndpointReturnsOk() {
        ResponseEntity<String> response = restTemplate.getForEntity(url("/actuator/springwolf"), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotBlank();
    }

    @Test
    void asyncApiResourceTest() throws Exception {
        String actual = restTemplate
                .getForEntity(url("/actuator/springwolf"), String.class)
                .getBody();
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actual).isEqualTo(expected);
    }

    private String url(String path) {
        return "http://localhost:%d%s".formatted(managementPort, path);
    }
}
