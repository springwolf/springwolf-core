package io.github.stavshamir.springwolf.example;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * This test is strongly coupled with the configuration of the springdoc openapi plugin in build.gradle
 * <p>
 * The gradle project is configured to run the generateOpenApiDocs task before the test task.
 * The generateOpenApiDocs task will generate the openapi-generated.json into the test-resources so that
 * this test can pick up the openapi-generated.json afterwards and compare it to the reference asyncapi.json
 */
public class OpenApiGeneratorTest {

    @Test
    public void asyncApiResourceArtifactTest() throws JSONException, IOException {

        InputStream expectedStream  = this.getClass().getResourceAsStream("/asyncapi.json");

        // When running with EmbeddedKafka, localhost is used as hostname
        String expectedWithoutServersKafkaUrlPatch = IOUtils.toString(expectedStream, StandardCharsets.UTF_8);
        String expected = expectedWithoutServersKafkaUrlPatch.replace("kafka:29092", "localhost:29092");

        InputStream actualStream = this.getClass().getResourceAsStream("/openapi-generated.json");
        String actual = IOUtils.toString(actualStream, StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT_ORDER);
    }
}
