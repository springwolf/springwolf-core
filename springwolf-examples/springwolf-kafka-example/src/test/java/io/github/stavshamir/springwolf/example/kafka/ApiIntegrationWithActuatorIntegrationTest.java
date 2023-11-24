// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfKafkaExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
            "springwolf.endpoint.actuator.enabled=true",
            "management.endpoints.web.exposure.include=springwolf"
        })
@EmbeddedKafka(partitions = 1)
@DirtiesContext
public class ApiIntegrationWithActuatorIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int managementPort;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        String url = "http://localhost:" + managementPort + "/actuator/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expectedWithoutServersKafkaUrlPatch = new String(s.readAllBytes(), StandardCharsets.UTF_8);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String expected = expectedWithoutServersKafkaUrlPatch.replace("kafka:29092", bootstrapServers);

        assertEquals(expected, actual);
    }
}
