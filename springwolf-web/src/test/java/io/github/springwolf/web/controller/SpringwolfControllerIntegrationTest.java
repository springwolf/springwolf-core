// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.web.controller;

import io.github.springwolf.web.fixtures.SpringwolfApplicationIntegrationTest;
import io.github.springwolf.web.integrationtests.application.basic.TestApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringwolfApplicationIntegrationTest
@RequiredArgsConstructor
class SpringwolfControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestClient restClient = RestClient.create();

    @Test
    void asyncApiEndpointReturnsOk() {
        ResponseEntity<String> response =
                restClient.get().uri(url("/springwolf/docs")).retrieve().toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotBlank();
    }

    @Test
    void unknownGroupReturnsNotFound() {
        assertThatThrownBy(() -> restClient
                        .get()
                        .uri(url("/springwolf/docs/not-a-group"))
                        .retrieve()
                        .toEntity(String.class))
                .isInstanceOf(HttpClientErrorException.NotFound.class)
                .satisfies(exception -> {
                    HttpClientErrorException notFoundException = (HttpClientErrorException) exception;
                    assertThat(notFoundException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(notFoundException.getResponseHeaders().getContentType())
                            .isEqualTo(MediaType.APPLICATION_PROBLEM_JSON);
                    assertThat(notFoundException.getResponseBodyAsString())
                            .contains("\"status\":404")
                            .doesNotContain("\"trace\"");
                });
    }

    private String url(String path) {
        return "http://localhost:%d%s".formatted(port, path);
    }
}
