package io.github.stavshamir.springwolf.example;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real kafka instance.
 */
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ApiIntegrationWithDockerTest {

    private final static RestTemplate restTemplate = new RestTemplate();
    private final static String APP_NAME = "app_1";
    private final static int APP_PORT = 8080;
    @ClassRule
    public static DockerComposeContainer<?> environment = new DockerComposeContainer<>(new File("docker-compose.yml"))
            .withExposedService(APP_NAME, APP_PORT);

    private String baseUrl() {
        String host = environment.getServiceHost(APP_NAME, APP_PORT);
        int port = environment.getServicePort(APP_NAME, APP_PORT);
        return String.format("http://%s:%d", host, port);
    }

    @Test
    public void asyncapiDocsShouldReturnTheCorrectJsonResponse() throws IOException, JSONException {
        String url = baseUrl() + "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER);
    }
}
