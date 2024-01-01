// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual);
    }
}
