// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Api integrationtest base class defining a SpringBootTest and a test method which asserts the resulting asyncapi.
 * Subclasses can customize this test with @TestPropertySources and custom expectation file names.
 * @see ApiWithDocketBeanIntegrationTest
 * @see ApiWithDocketFromEnvironmentIntegrationTest
 */
@SpringBootTest(
        classes = {SpringwolfAmqpExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    void asyncApiResourceArtifactTest() throws JSONException, IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        String expectedApiFileName = getExpectedApiFileName();
        InputStream s = this.getClass().getResourceAsStream(expectedApiFileName);
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual);
    }

    protected abstract String getExpectedApiFileName();
}
