// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.model.server;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class ServerTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeServer() throws Exception {
        Server server = Server.builder()
                .host("localhost:5672")
                .description("Development AMQP broker.")
                .protocol("amqp")
                .protocolVersion("0-9-1")
                .tags(List.of(Tag.builder()
                        .name("env:development")
                        .description("This environment is meant for developers to run their own tests.")
                        .build()))
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/server/server.json");
        assertThatJson(serializer.toJsonString(server)).isEqualTo(example);
    }
}
