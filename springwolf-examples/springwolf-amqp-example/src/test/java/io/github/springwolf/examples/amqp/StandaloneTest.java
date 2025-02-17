// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.standalone.StandaloneConfigurationDiscoverer;
import io.github.springwolf.core.standalone.StandaloneDIFactory;
import io.github.springwolf.core.standalone.StandaloneEnvironmentLoader;
import io.github.springwolf.core.standalone.StandaloneFactory;
import io.github.springwolf.examples.amqp.configuration.RabbitConfiguration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StandaloneTest {

    @Test
    public void scanApplication() throws IOException {
        // given
        String basePackage = "io.github.springwolf.examples.amqp";

        List<String> profiles = List.of();
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.loadEnvironment(profiles);
        List<Class<?>> configurations = StandaloneConfigurationDiscoverer.discover(environment);
        StandaloneFactory standaloneFactory = new StandaloneDIFactory(basePackage, configurations, environment);

        // when
        AsyncAPI asyncApi = standaloneFactory.getAsyncApiService().getAsyncAPI();

        // then
        assertThat(asyncApi).isNotNull();

        AsyncApiSerializerService serializerService = new DefaultAsyncApiSerializerService();
        String actual = serializerService.toJsonString(asyncApi);
        String actualPatched = actual.replace("localhost:5672", "amqp:5672");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.standalone.json"), actualPatched);

        // then
        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();

        assertEquals(
                removeDifferencesDueToAmqpSpringBeans(serializerService, expected), //
                removeDifferencesDueToAmqpSpringBeans(serializerService, actualPatched));
    }

    /**
     * In spring-amqp, queues can be configured via Spring beans, see {@link RabbitConfiguration}
     *
     * This is not supported of the standalone mode, therefore those changes are ignored
     */
    @SneakyThrows
    String removeDifferencesDueToAmqpSpringBeans(AsyncApiSerializerService serializerService, String str) {
        JsonNode node = JsonKeyRemover.removeKeys(str, List.of("components", "messages"));
        return serializerService.toJsonString(node);
    }
}
