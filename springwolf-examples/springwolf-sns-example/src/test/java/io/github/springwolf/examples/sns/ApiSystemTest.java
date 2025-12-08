// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real sns instance.
 */
@Testcontainers
@Slf4j
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ApiSystemTest {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final String APP_NAME = "app";
    private static final int APP_PORT = 8080;

    @Container
    public static ComposeContainer environment = new ComposeContainer(new File("docker-compose.yml"))
            .withCopyFilesInContainer(".env") // do not copy all files in the directory
            .withExposedService(APP_NAME, APP_PORT)
            .waitingFor(APP_NAME, Wait.forLogMessage(".*AsyncAPI document was built.*", 1))
            .withLogConsumer(APP_NAME, l -> Arrays.stream(
                            l.getUtf8StringWithoutLineEnding().split("(\n|\r\n)"))
                    .forEach(m -> log.debug("APP: {}", m)));

    private String baseUrl() {
        String host = environment.getServiceHost(APP_NAME, APP_PORT);
        int port = environment.getServicePort(APP_NAME, APP_PORT);
        return "http://%s:%d".formatted(host, port);
    }

    @Test
    void asyncapiDocsShouldReturnTheCorrectJsonResponse() throws Exception {
        String url = baseUrl() + "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actual).isEqualTo(expected);
    }
}
