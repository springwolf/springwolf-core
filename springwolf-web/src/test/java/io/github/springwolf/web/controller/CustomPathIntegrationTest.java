// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.controller;

import io.github.springwolf.web.fixtures.SpringwolfApplicationIntegrationTest;
import io.github.springwolf.web.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringwolfApplicationIntegrationTest
@TestPropertySource(
        properties = {"springwolf.path.base=/my-custom/springwolf/endpoint/test", "springwolf.path.docs=/apispec"})
class CustomPathIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestClient restClient = RestClient.create();

    @Test
    void asyncApiEndpointReturnsOkAtCustomPath() {
        ResponseEntity<String> response = restClient
                .get()
                .uri(url("/my-custom/springwolf/endpoint/test/apispec"))
                .retrieve()
                .toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotBlank();
    }

    @Test
    void asyncApiUiReturnsOkAtCustomPath() {
        ResponseEntity<String> response = restClient
                .get()
                .uri(url("/my-custom/springwolf/endpoint/test/asyncapi-ui.html"))
                .retrieve()
                .toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotBlank();
    }

    private String url(String path) {
        return "http://localhost:%d%s".formatted(port, path);
    }
}
