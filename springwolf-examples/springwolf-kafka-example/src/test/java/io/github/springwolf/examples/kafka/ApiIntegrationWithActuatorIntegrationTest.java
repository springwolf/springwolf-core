// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
            "springwolf.endpoint.actuator.enabled=true",
            "management.endpoints.web.exposure.include=springwolf"
        })
@AutoConfigureRestTestClient
public class ApiIntegrationWithActuatorIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @LocalServerPort
    private int managementPort;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "http://localhost:" + managementPort + "/actuator/springwolf";
        String actual = restTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        String expectedWithoutServersKafkaUrlPatch;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            expectedWithoutServersKafkaUrlPatch = new String(s.readAllBytes(), StandardCharsets.UTF_8);
        }
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String expected = expectedWithoutServersKafkaUrlPatch
                .replace("kafka:29092", bootstrapServers)
                .trim();

        assertThat(actual).isEqualTo(expected);
    }
}
