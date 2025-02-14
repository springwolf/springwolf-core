// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.standalone;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import io.github.springwolf.examples.kafka.standalone.common.SpringwolfConfigPropertiesLoader;
import io.github.springwolf.examples.kafka.standalone.plugin.StandalonePlugin;
import io.github.springwolf.examples.kafka.standalone.plugin.StandalonePluginDiscovery;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandaloneTest {

    @Test
    public void scanApplication()
            throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        // given
        String basePackage = "io.github.springwolf.examples.kafka";

        List<String> profiles = List.of();
        Environment environment = new SpringwolfConfigPropertiesLoader().loadEnvironment(profiles);
        List<StandalonePlugin> plugins = StandalonePluginDiscovery.scan(environment);
        StandaloneFactory standaloneFactory = new DefaultStandaloneFactory(basePackage, plugins, environment);
        AsyncApiService asyncApiService = standaloneFactory.getAsyncApiService();

        // when
        AsyncAPI asyncApi = asyncApiService.getAsyncAPI();

        // then
        assertThat(asyncApi).isNotNull();

        AsyncApiSerializerService serializerService = new DefaultAsyncApiSerializerService();
        String actual = serializerService.toJsonString(asyncApi);
        String actualPatched = actual.replace("localhost:9092", "kafka:29092");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.standalone.json"), actualPatched);

        // then
        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        assertEquals(expected, actualPatched);
    }
}
