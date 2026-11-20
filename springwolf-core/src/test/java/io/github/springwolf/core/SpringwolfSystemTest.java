// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core;

import io.github.springwolf.core.fixtures.SpringwolfApplicationIntegrationTest;
import io.github.springwolf.core.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringwolfApplicationIntegrationTest
class SpringwolfSystemTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void asyncApiEndpointReturnsOk() {
        ResponseEntity<String> response = restTemplate.getForEntity(url("/springwolf/docs"), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotBlank();
    }

    @Test
    void unknownGroupReturnsNotFound() {
        assertThatThrownBy(() -> restTemplate.getForEntity(url("/springwolf/docs/not-a-group"), String.class))
                .isInstanceOf(HttpClientErrorException.NotFound.class)
                .satisfies(exception -> assertThat(((HttpClientErrorException) exception).getStatusCode())
                        .isEqualTo(HttpStatus.NOT_FOUND));
    }

    private String url(String path) {
        return "http://localhost:%d%s".formatted(port, path);
    }
}
