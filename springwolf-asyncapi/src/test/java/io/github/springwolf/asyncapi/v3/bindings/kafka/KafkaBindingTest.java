// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.asyncapi.v3.bindings.kafka;

import io.github.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializerService;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.asyncapi.v3.model.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class KafkaBindingTest {
    private static final DefaultAsyncApiSerializerService serializer = new DefaultAsyncApiSerializerService();

    @Test
    void shouldSerializeKafkaServerBinding() throws IOException {
        var servers = Map.of(
                "servers",
                Map.of(
                        "production",
                        Server.builder()
                                .bindings(Map.of(
                                        "kafka",
                                        KafkaServerBinding.builder()
                                                .schemaRegistryUrl("https://my-schema-registry.com")
                                                .schemaRegistryVendor("confluent")
                                                .build()))
                                .build()));

        // Uses https://github.com/asyncapi/bindings/blob/master/kafka/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/kafka/kafka-server.yaml");
        assertThatJson(serializer.toJsonString(servers)).isEqualTo(example);
    }

    @Test
    void shouldSerializeKafkaOperationBinding() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .operations(Map.of(
                        "userSignup",
                        Operation.builder()
                                .action(OperationAction.RECEIVE)
                                .bindings(Map.of(
                                        "kafka",
                                        KafkaOperationBinding.builder()
                                                .groupId(SchemaObject.builder()
                                                        .type(SchemaType.STRING)
                                                        .enumValues(List.of("myGroupId"))
                                                        .build())
                                                .clientId(SchemaObject.builder()
                                                        .type(SchemaType.STRING)
                                                        .enumValues(List.of("myClientId"))
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/kafka/kafka-operation.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeKafkaChannel() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "user-signedup",
                        ChannelObject.builder()
                                .bindings(Map.of(
                                        "kafka",
                                        KafkaChannelBinding.builder()
                                                .topic("my-specific-topic-name")
                                                .partitions(20)
                                                .replicas(3)
                                                .topicConfiguration(KafkaChannelTopicConfiguration.builder()
                                                        .cleanupPolicy(List.of(
                                                                KafkaChannelTopicCleanupPolicy.DELETE,
                                                                KafkaChannelTopicCleanupPolicy.COMPACT))
                                                        .retentionMs(604800000L)
                                                        .retentionBytes(1000000000L)
                                                        .deleteRetentionMs(86400000L)
                                                        .maxMessageBytes(1048588)
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // https://github.com/asyncapi/bindings/blob/master/kafka/README.md#example-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/kafka/kafka-channel.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeKafkaTopic() throws IOException {

        var topic = Map.of(
                "topicConfiguration",
                KafkaChannelTopicConfiguration.builder()
                        .cleanupPolicy(
                                List.of(KafkaChannelTopicCleanupPolicy.DELETE, KafkaChannelTopicCleanupPolicy.COMPACT))
                        .retentionMs(604800000L)
                        .retentionBytes(1000000000L)
                        .deleteRetentionMs(86400000L)
                        .maxMessageBytes(1048588)
                        .confluentKeySchemaValidation(true)
                        .confluentKeySubjectNameStrategy("TopicNameStrategy")
                        .confluentValueSchemaValidation(true)
                        .confluentValueSubjectNameStrategy("TopicNameStrategy")
                        .build());

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/kafka/kafka-topic.yaml");
        assertThatJson(serializer.toJsonString(topic)).isEqualTo(example);
    }

    @Test
    void shouldSerializeKafkaMessage() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "test",
                        ChannelObject.builder()
                                .address("test-topic")
                                .messages(Map.of(
                                        "testMessage",
                                        MessageObject.builder()
                                                .bindings(Map.of(
                                                        "kafka",
                                                        KafkaMessageBinding.builder()
                                                                .key(
                                                                        SchemaObject.builder()
                                                                                .type(SchemaType.STRING)
                                                                                .enumValues(List.of("myKey"))
                                                                                .build())
                                                                .schemaIdLocation("payload")
                                                                .schemaIdPayloadEncoding("apicurio-new")
                                                                .schemaLookupStrategy("TopicIdStrategy")
                                                                .build()))
                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/kafka/README.md#message-binding-object
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/kafka/kafka-message.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }
}
