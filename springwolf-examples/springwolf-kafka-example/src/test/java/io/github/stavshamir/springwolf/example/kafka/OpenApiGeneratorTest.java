package io.github.stavshamir.springwolf.example.kafka;

import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This test is strongly coupled with the configuration of the springdoc openapi plugin in build.gradle
 * <p>
 * The gradle project is configured to run the generateOpenApiDocs task before the test task.
 * The generateOpenApiDocs task will generate the openapi-generated.json into the test-resources so that
 * this test can pick up the openapi-generated.json afterwards and compare it to the reference asyncapi.json
 */
class OpenApiGeneratorTest {

    @Test
    void asyncApiResourceArtifactTest() throws IOException {
        InputStream expectedStream = this.getClass().getResourceAsStream("/asyncapi.json");
        String expectedWithoutServersKafkaUrlPatch = IOUtils.toString(expectedStream, StandardCharsets.UTF_8);
        // When running with EmbeddedKafka, localhost is used as hostname
        String expected = expectedWithoutServersKafkaUrlPatch.replace("kafka:29092", "localhost:9092");

        InputStream actualStream = this.getClass().getResourceAsStream("/openapi-generated.json");
        String actual = IOUtils.toString(actualStream, StandardCharsets.UTF_8)
                .replace("\\u003d", "="); // openapi generator replaces equal (=) with its unicode representation

        // openapi generator uses a different formatter, therefore removing spaces and newlines during comparison
        assertEquals(expected.replaceAll("\\s", ""), actual.replaceAll("\\s", ""));
    }
}
