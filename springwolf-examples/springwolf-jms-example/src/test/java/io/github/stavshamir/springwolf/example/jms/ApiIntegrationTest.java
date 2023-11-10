// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.jms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = {SpringwolfJmsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({JmsTestContainerExtension.class})
public class ApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expectedWithoutServersJmsUrlPatch =
                new String(s.readAllBytes(), StandardCharsets.UTF_8).replace("\r\n", "\n");
        String expected = expectedWithoutServersJmsUrlPatch.replace("activemq:61616", "localhost:61616");

        assertEquals(expected, actual);
    }
}
