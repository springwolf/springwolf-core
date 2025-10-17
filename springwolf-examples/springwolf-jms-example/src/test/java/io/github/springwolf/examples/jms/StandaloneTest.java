// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import io.github.springwolf.asyncapi.v3.jackson.AsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.core.standalone.DefaultStandaloneApplication;
import io.github.springwolf.core.standalone.StandaloneApplication;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class StandaloneTest {

    @Test
    void asyncApiStandaloneArtifactTest() throws Exception {
        // given
        StandaloneApplication standaloneApplication =
                DefaultStandaloneApplication.builder().buildAndStart();

        // when
        AsyncAPI asyncApi = standaloneApplication.getAsyncApiService().getAsyncAPI();

        // then
        assertThat(asyncApi).isNotNull();

        AsyncApiSerializerService serializerService = new DefaultAsyncApiSerializerService();
        String actual = serializerService.toJsonString(asyncApi);
        String actualPatched = actual.replace("localhost:61616", "activemq:61616");
        Files.writeString(Path.of("src", "test", "resources", "asyncapi.standalone.json"), actualPatched);

        // then
        InputStream s = this.getClass().getResourceAsStream("/asyncapi.json");
        String expected = new String(s.readAllBytes(), StandardCharsets.UTF_8).trim();
        assertThat(actualPatched).isEqualTo(expected);
    }
}
