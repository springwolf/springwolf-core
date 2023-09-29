package io.github.stavshamir.springwolf.example.kafka;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
        properties = {"springwolf.use-management-port=true", "management.endpoints.web.exposure.include=springwolf"})
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {
            "listeners=PLAINTEXT://localhost:9092",
            "port=9092",
        })
@DirtiesContext
public class ApiIntegrationWithActuatorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int managementPort;

    @Test
    void asyncApiResourceArtifactTest() throws JSONException, IOException {
        String url = "http://localhost:" + managementPort + "/actuator/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expectedWithoutServersKafkaUrlPatch = new String(s.readAllBytes(), StandardCharsets.UTF_8);
        // When running with EmbeddedKafka, localhost is used as hostname
        String expected = expectedWithoutServersKafkaUrlPatch.replace("kafka:29092", "127.0.0.1:9092");

        assertEquals(expected, actual);
    }
}
