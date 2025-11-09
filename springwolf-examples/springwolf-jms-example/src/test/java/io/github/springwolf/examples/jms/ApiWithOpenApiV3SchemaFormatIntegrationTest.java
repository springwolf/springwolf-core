// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfJmsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({JmsTestContainerExtension.class})
@TestPropertySource(properties = {"springwolf.docket.payload-schema-format=openapi_v3"})
public class ApiWithOpenApiV3SchemaFormatIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${server.port}")
    public Integer serverPort;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        String actualPatched = actual.replace("localhost:61616", "activemq:61616");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.openapiv3.actual.json"), actualPatched);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.openapiv3.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actualPatched).isEqualTo(expected);
    }
}
