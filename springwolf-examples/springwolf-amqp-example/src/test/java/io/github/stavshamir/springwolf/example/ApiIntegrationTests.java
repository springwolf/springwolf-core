package io.github.stavshamir.springwolf.example;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = {SpringwolfExampleApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    public void asyncApiResourceArtifactTest() throws JSONException, IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER);
    }
}