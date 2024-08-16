// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.mqtt;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class MQTTBindingTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeMQTTServerExample1() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .servers(Map.of(
                        "production",
                        Server.builder()
                                .bindings(Map.of(
                                        "mqtt",
                                        MQTTServerBinding.builder()
                                                .clientId("guest")
                                                .cleanSession(true)
                                                .lastWill(MQTTServerLastWill.builder()
                                                        .topic("/last-wills")
                                                        .qos(2)
                                                        .message("Guest gone offline.")
                                                        .retain(false)
                                                        .build())
                                                .keepAlive(60)
                                                .sessionExpiryInterval(600)
                                                .maximumPacketSize(1200)
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/mqtt/mqtt-server-1.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeMQTTServerExample2() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .servers(Map.of(
                        "production",
                        Server.builder()
                                .bindings(Map.of(
                                        "mqtt",
                                        MQTTServerBinding.builder()
                                                .sessionExpiryInterval(SchemaObject.builder()
                                                        .type(SchemaType.INTEGER)
                                                        .minimum(new BigDecimal("30"))
                                                        .maximum(new BigDecimal("1200"))
                                                        .build())
                                                .maximumPacketSize(SchemaObject.builder()
                                                        .type(SchemaType.INTEGER)
                                                        .minimum(new BigDecimal("256"))
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/mqtt/mqtt-server-2.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    // Operation Binding is not tested, the examples are not AsyncAPI v3 compatible
}
