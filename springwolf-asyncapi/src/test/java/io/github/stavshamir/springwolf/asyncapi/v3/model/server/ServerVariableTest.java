// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.model.server;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class ServerVariableTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeServerVariable() throws IOException {
        Server serverVariable = Server.builder()
                .host("rabbitmq.in.mycompany.com:5672")
                .pathname("/{env}")
                .protocol("amqp")
                .description("RabbitMQ broker. Use the `env` variable to point to either `production` or `staging`.")
                .variables(Map.of(
                        "env",
                        ServerVariable.builder()
                                .description("Environment to connect to. It can be either `production` or `staging`.")
                                .enumValues(List.of("production", "staging"))
                                .build()))
                .build();

        String example = ClasspathUtil.readAsString("/v3/model/server/server-variable.json");
        assertThatJson(serializer.toJsonString(serverVariable)).isEqualTo(example);
    }
}
