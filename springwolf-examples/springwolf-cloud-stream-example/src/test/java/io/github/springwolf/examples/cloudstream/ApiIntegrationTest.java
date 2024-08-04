// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.cloudstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfCloudstreamExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(
        partitions = 1,
        brokerProperties = {"listeners=PLAINTEXT://localhost:9095", "port=9095"})
@DirtiesContext
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        // When running with EmbeddedKafka, the kafka bootstrap server does run on random ports
        String actualPatched = actual.replace(bootstrapServers, "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actualPatched);
    }
}
