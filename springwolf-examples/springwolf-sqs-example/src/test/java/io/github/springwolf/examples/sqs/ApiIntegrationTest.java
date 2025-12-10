// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {SpringwolfSqsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SqsTestContainerExtension.class})
@AutoConfigureTestRestTemplate
class ApiIntegrationTest {

    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SqsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void asyncApiResourceArtifactTest() throws Exception {
        String url = "/springwolf/docs";
        String actual = restTemplate.getForObject(url, String.class);
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.actual.json"), actual);

        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertThat(actual).isEqualTo(expected);
    }
}
