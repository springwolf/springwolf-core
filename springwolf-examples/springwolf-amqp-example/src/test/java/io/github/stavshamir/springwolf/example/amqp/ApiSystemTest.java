// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real kafka instance.
 */
@Slf4j
@Testcontainers
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ApiSystemTest {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String APP_NAME = "app_1";
    private static final int APP_PORT = 8080;

    private static final Map<String, String> ENV = new HashMap<>();

    static {
        try (InputStream input = new FileInputStream(".env")) {
            var properties = new Properties();
            properties.load(input);
            properties.forEach((key, value) -> ENV.put(String.valueOf(key), String.valueOf(value)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Container
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withExposedService(APP_NAME, APP_PORT)
            .withEnv(ENV)
            .withLogConsumer(APP_NAME, l -> log.debug("APP: %s".formatted(l.getUtf8StringWithoutLineEnding())));

    private String baseUrl() {
        String host = environment.getServiceHost(APP_NAME, APP_PORT);
        int port = environment.getServicePort(APP_NAME, APP_PORT);
        return String.format("http://%s:%d", host, port);
    }

    @Test
    void asyncapiDocsShouldReturnTheCorrectJsonResponse() throws IOException {
        String url = baseUrl() + "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(expected, actual);
    }
}
