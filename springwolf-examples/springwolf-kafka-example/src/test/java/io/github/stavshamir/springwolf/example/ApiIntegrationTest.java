package io.github.stavshamir.springwolf.example;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = {SpringwolfExampleApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EmbeddedKafka(
        partitions = 1, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092",
})
@DirtiesContext
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    public void asyncApiResourceArtifactTest() throws JSONException, IOException {
        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = IOUtils.toString(s, StandardCharsets.UTF_8);

        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER);
    }
}
