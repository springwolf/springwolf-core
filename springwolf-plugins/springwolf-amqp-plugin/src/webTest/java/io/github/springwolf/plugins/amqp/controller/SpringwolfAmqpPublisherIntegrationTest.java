// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.controller;

import io.github.springwolf.plugins.amqp.fixture.SpringwolfIntegrationTest;
import io.github.springwolf.plugins.amqp.integrationtests.application.basic.TestApplication;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.json.JsonMapper;

import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringwolfIntegrationTest
@RequiredArgsConstructor
class SpringwolfAmqpPublisherIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JsonMapper jsonMapper;

    @MockitoBean
    private RabbitTemplate rabbitTemplate;

    @Test
    void shouldPublishTest() {
        // given
        Map<String, Object> message = Map.of(
                "bindings", Map.of("binding", "binding-value"),
                "headers", Map.of("header", "header-value"),
                "type", String.class.getCanonicalName(),
                "payload", "\"payload\"");
        HttpEntity<String> request = toRequest(message);

        // when
        restTemplate.postForObject(url("/springwolf/plugin/amqp/publish?topic=example-topic"), request, String.class);

        // then
        verify(rabbitTemplate).convertAndSend(eq(""), eq("example-topic"), eq("payload"));
    }

    private HttpEntity<String> toRequest(Object message) {
        String body = jsonMapper.writeValueAsString(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    private String url(String path) {
        return "http://localhost:%d%s".formatted(port, path);
    }
}
