// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.sns;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfSnsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SnsTestContainerExtension.class})
class ApiIntegrationTest {

    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SnsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    void asyncApiResourceArtifactTest() throws JSONException, IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        System.out.println("Got: " + actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8);

        assertEquals(expected, actual);
    }
}
