// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfJmsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({JmsTestContainerExtension.class})
@AutoConfigureRestTestClient
public class ApiIntegrationTest {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = Objects.requireNonNull(
                restTestClient
                        .get()
                        .uri(url)
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(String.class)
                        .returnResult()
                        .getResponseBody(),
                "Expected AsyncAPI response body");
        String actualPatched = actual.replace("localhost:61616", "activemq:61616");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actualPatched);

        String expected;
        try (InputStream s = this.getClass().getResourceAsStream("/asyncapi.json")) {
            expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        }

        assertThat(actualPatched).isEqualTo(expected);
    }
}
