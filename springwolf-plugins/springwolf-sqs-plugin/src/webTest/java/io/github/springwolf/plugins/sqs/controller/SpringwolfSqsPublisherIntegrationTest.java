// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.controller;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.plugins.sqs.fixture.SpringwolfIntegrationTest;
import io.github.springwolf.plugins.sqs.integrationtests.application.basic.TestApplication;
import io.github.springwolf.plugins.sqs.publish.SpringwolfSqsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.json.JsonMapper;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringwolfIntegrationTest
class SpringwolfSqsPublisherIntegrationTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private JsonMapper jsonMapper;

    @Autowired
    private SchemasService schemasService;

    @MockitoBean
    private SpringwolfSqsPublisher publisher;

    @BeforeEach
    void setup() {
        when(publisher.isEnabled()).thenReturn(true);
        schemasService.registerSchema(SchemaObject.builder()
                .title(String.class.getName())
                .properties(new HashMap<>())
                .build());
    }

    @Test
    void publishEndpointSendsMessageToSqsPublisher() {
        // given
        HttpEntity<String> request = toRequest();

        // when
        restTemplate.postForObject(url(), request, String.class);

        // then
        verify(publisher).send(eq("example-topic"), anyMap(), eq("payload"));
    }

    private HttpEntity<String> toRequest() {
        String body = jsonMapper.writeValueAsString(
                Map.of("headers", Map.of(), "type", String.class.getCanonicalName(), "payload", "\"payload\""));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }

    private String url() {
        return "http://localhost:%d/springwolf/plugin/sqs/publish?topic=example-topic".formatted(port);
    }
}
