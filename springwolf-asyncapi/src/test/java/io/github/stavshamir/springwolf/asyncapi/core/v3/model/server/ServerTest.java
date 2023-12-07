// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.core.v3.model.server;

import io.github.stavshamir.springwolf.asyncapi.core.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.core.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.core.v3.model.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class ServerTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeServer() throws IOException {
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
