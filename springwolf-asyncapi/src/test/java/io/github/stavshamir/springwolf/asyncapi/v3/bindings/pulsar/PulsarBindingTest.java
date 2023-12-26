// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.pulsar;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.v3.model.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class PulsarBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializePulsarServer() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .servers(Map.of(
                        "production",
                        Server.builder()
                                .bindings(Map.of(
                                        "pulsar",
                                        PulsarServerBinding.builder()
                                                .tenant("contoso")
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/pulsar/pulsar-server.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializePulsarChannel() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        Channel.builder()
                                .bindings(Map.of(
                                        "pulsar",
                                        PulsarChannelBinding.builder()
                                                .namespace("staging")
                                                .persistence(PulsarChannelBinding.PulsarPesistenceType.PERSISTENCE)
                                                .compaction(1000)
                                                .georeplication(List.of("us-east1", "us-west1"))
                                                .retention(PulsarRetention.builder()
                                                        .time(7)
                                                        .size(1000)
                                                        .build())
                                                .ttl(360)
                                                .deduplication(false)
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/mqtt/README.md#examples
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/pulsar/pulsar-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }
}
