// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.googlepubsub;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.components.Components;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class GooglePubSubBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeGooglePubSubMessage() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .components(Components.builder()
                        .messages(Map.of(
                                "messageAvro",
                                MessageObject.builder()
                                        .bindings(Map.of(
                                                "googlepubsub",
                                                GooglePubSubMessageBinding.builder()
                                                        .schema(GooglePubSubSchema.builder()
                                                                .name("projects/your-project/schemas/message-avro")
                                                                .build())
                                                        .build()))
                                        .contentType("application/json")
                                        .name("MessageAvro")
                                        .payload(MessagePayload.of(MultiFormatSchema.builder()
                                                .schemaFormat("application/vnd.apache.avro+yaml;version=1.9.0")
                                                .schema(Map.of(
                                                        "fields",
                                                        List.of(Map.of("name", "message", "type", "string")),
                                                        "name",
                                                        "Message",
                                                        "type",
                                                        "record"))
                                                .build()))
                                        .build(),
                                "messageProto",
                                MessageObject.builder()
                                        .bindings(Map.of(
                                                "googlepubsub",
                                                GooglePubSubMessageBinding.builder()
                                                        .schema(GooglePubSubSchema.builder()
                                                                .name("projects/your-project/schemas/message-proto")
                                                                .build())
                                                        .build()))
                                        .contentType("application/octet-stream")
                                        .name("MessageProto")
                                        .payload(MessagePayload.of(MultiFormatSchema.builder()
                                                .schemaFormat("application/vnd.google.protobuf;version=3")
                                                .schema(
                                                        """
                                                        syntax = "proto3";

                                                        message Message {
                                                          required string message = 1;
                                                        }
                                                        """)
                                                .build()))
                                        .build()))
                        .build())
                .build();

        // https://github.com/asyncapi/bindings/blob/master/googlepubsub/README.md#example-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/googlepubsub/googlepubsub-message.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths(
                        "asyncapi",
                        "components.messages.messageAvro.bindings.googlepubsub.bindingVersion",
                        "components.messages.messageProto.bindings.googlepubsub.bindingVersion")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeGooglePubSubChannel() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "topic-avro-schema",
                        ChannelObject.builder()
                                .address("projects/your-project/topics/topic-avro-schema")
                                .bindings(Map.of(
                                        "googlepubsub",
                                        GooglePubSubChannelBinding.builder()
                                                .schemaSettings(GooglePubSubSchemaSettings.builder()
                                                        .encoding("json")
                                                        .name("projects/your-project/schemas/message-avro")
                                                        .build())
                                                .build()))
                                .build(),
                        "topic-proto-schema",
                        ChannelObject.builder()
                                .address("projects/your-project/topics/topic-proto-schema")
                                .bindings(Map.of(
                                        "googlepubsub",
                                        GooglePubSubChannelBinding.builder()
                                                .messageRetentionDuration("86400s")
                                                .messageStoragePolicy(GooglePubSubMessageStoragePolicy.builder()
                                                        .allowedPersistenceRegions(List.of(
                                                                "us-central1",
                                                                "us-central2",
                                                                "us-east1",
                                                                "us-east4",
                                                                "us-east5",
                                                                "us-east7",
                                                                "us-south1",
                                                                "us-west1",
                                                                "us-west2",
                                                                "us-west3",
                                                                "us-west4"))
                                                        .build())
                                                .schemaSettings(GooglePubSubSchemaSettings.builder()
                                                        .encoding("binary")
                                                        .name("projects/your-project/schemas/message-proto")
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/googlepubsub/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/googlepubsub/googlepubsub-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths(
                        "asyncapi",
                        "channels.topic-avro-schema.bindings.googlepubsub.bindingVersion",
                        "channels.topic-proto-schema.bindings.googlepubsub.bindingVersion")
                .isEqualTo(example);
    }
}
