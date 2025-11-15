// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.standalone.DefaultStandaloneApplication;
import io.github.springwolf.core.standalone.StandaloneApplication;
import io.github.springwolf.core.standalone.StandaloneEnvironmentLoader;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PayloadSchemaFormatIntegrationTest {
    @Test
    void asyncApiStandaloneArtifactTest() throws Exception {
        // given
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load();
        environment
                .getPropertySources()
                .addFirst(new MapPropertySource(
                        "env", Map.of("springwolf.docket.payload-schema-format", "openapi_v3_1")));
        StandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .setEnvironment(environment)
                .buildAndStart();

        // when
        AsyncAPI asyncApi = standaloneApplication.getAsyncApiService().getAsyncAPI();

        // then
        assertThat(asyncApi).isNotNull();

        AsyncApiSerializerService serializerService = new DefaultAsyncApiSerializerService();
        String actual = serializerService.toJsonString(asyncApi);
        String actualPatched = actual.replace("localhost:9092", "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.openapiv31.actual.json"), actualPatched);

        // then
        InputStream s = this.getClass().getResourceAsStream("/asyncapi.openapiv31.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        assertThat(actualPatched).isEqualTo(expected);
    }
}
