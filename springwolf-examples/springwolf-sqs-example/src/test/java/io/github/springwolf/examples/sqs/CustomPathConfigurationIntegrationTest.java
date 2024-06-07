// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfSqsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SqsTestContainerExtension.class})
@TestPropertySource(
        properties = {"springwolf.path.base=/my-custom/springwolf/endpoint/test", "springwolf.path.docs=/apispec"})
class CustomPathConfigurationIntegrationTest {

    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SqsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getSpec() {
        String url = "/my-custom/springwolf/endpoint/test/apispec";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void canPublish() {
        String url = "/my-custom/springwolf/endpoint/test/sqs/publish";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void asyncapiDocsShouldReturnTheCorrectJsonResponse() {
        String url = "/my-custom/springwolf/endpoint/test/asyncapi-ui.html";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
