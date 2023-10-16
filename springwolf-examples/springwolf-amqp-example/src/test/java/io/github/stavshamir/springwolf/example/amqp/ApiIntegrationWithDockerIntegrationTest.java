// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import org.json.JSONException;
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
@Testcontainers
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ApiIntegrationWithDockerIntegrationTest {

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
            .withEnv(ENV);

    private String baseUrl() {
        String host = environment.getServiceHost(APP_NAME, APP_PORT);
        int port = environment.getServicePort(APP_NAME, APP_PORT);
        return String.format("http://%s:%d", host, port);
    }

    @Test
    void asyncapiDocsShouldReturnTheCorrectJsonResponse() throws IOException, JSONException {
        String url = baseUrl() + "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi_withdocketbean.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual);
    }
}
