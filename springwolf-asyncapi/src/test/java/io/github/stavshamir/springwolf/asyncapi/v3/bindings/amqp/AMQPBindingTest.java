// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp;

import io.github.stavshamir.springwolf.asyncapi.v3.ClasspathUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.jackson.DefaultAsyncApiSerializer;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

class AMQPBindingTest {
    private static final DefaultAsyncApiSerializer serializer = new DefaultAsyncApiSerializer();

    @Test
    void shouldSerializeAMQPChannelBindingQueue() throws IOException {

        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "userSignup",
                        ChannelObject.builder()
                                .address("user/signup")
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPChannelBinding.builder()
                                                .is(AMQPChannelType.QUEUE)
                                                .queue(AMQPChannelQueueProperties.builder()
                                                        .name("my-queue-name")
                                                        .durable(true)
                                                        .exclusive(true)
                                                        .autoDelete(false)
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/amqp/amqp-channel-queue.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "operations")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeAMQPChannelBindingRouting() throws IOException {

        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "userSignup",
                        ChannelObject.builder()
                                .address("user/signup")
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPChannelBinding.builder()
                                                .is(AMQPChannelType.ROUTING_KEY)
                                                .exchange(AMQPChannelExchangeProperties.builder()
                                                        .name("myExchange")
                                                        .type(AMQPChannelExchangeType.TOPIC)
                                                        .durable(true)
                                                        .autoDelete(false)
                                                        .build())
                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#example
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/amqp/amqp-channel-routing.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi", "operations")
                .isEqualTo(example);
    }

    @Test
    void shouldSerializeAMQPOperationBinding() throws IOException {

        var asyncapi = AsyncAPI.builder()
                .operations(Map.of(
                        "userSignup",
                        Operation.builder()
                                .channel(ChannelReference.builder()
                                        .ref("#/channels/userSignup")
                                        .build())
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPOperationBinding.builder()
                                                .expiration(100000)
                                                .userId("guest")
                                                .cc(List.of("user.logs"))
                                                .priority(10)
                                                .deliveryMode(2)
                                                .mandatory(false)
                                                .bcc(List.of("external.audit"))
                                                .timestamp(true)
                                                .ack(false)
                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#example-1
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/amqp/amqp-operation.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }

    @Test
    @Disabled("FIXME: Allow messages to be a ref or a message object")
    void shouldSerializeAMQPMessageBinding() throws IOException {
        var asyncapi = AsyncAPI.builder()
                .channels(Map.of(
                        "userSignup",
                        ChannelObject.builder()
                                .address("user/signup")
                                //                                .messages(Map.of(
                                //                                        "userSignupMessage",
                                //                                        MessageObject.builder()
                                //                                                .bindings(Map.of(
                                //                                                        "amqp",
                                //                                                        AMQPMessageBinding.builder()
                                //
                                // .contentEncoding("gzip")
                                //
                                // .messageType("user.signup")
                                //                                                                .build()))
                                //                                                .build()))
                                .build()))
                .build();

        // Uses https://github.com/asyncapi/bindings/blob/master/amqp/README.md#message-binding-object
        var example = ClasspathUtil.parseYamlFile("/v3/bindings/amqp/amqp-message.yaml");
        assertThatJson(serializer.toJsonString(asyncapi))
                .whenIgnoringPaths("asyncapi")
                .isEqualTo(example);
    }
}
