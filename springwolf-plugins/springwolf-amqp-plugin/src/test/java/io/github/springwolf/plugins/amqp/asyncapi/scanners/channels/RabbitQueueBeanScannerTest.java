// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelQueueProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelType;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RabbitQueueBeanScannerTest {

    @Test
    void scan() {
        // given
        var queue = new Queue("name");
        var binding = new Binding("destination", Binding.DestinationType.QUEUE, "exchange", "routingKey", null);
        var scanner = new RabbitQueueBeanScanner(List.of(queue), List.of(binding));

        // when
        var result = scanner.scan();

        // then
        assertThat(result)
                .isEqualTo(Map.of(
                        "name",
                        ChannelObject.builder()
                                .channelId("name")
                                .address("name")
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPChannelBinding.builder()
                                                .is(AMQPChannelType.QUEUE)
                                                .queue(AMQPChannelQueueProperties.builder()
                                                        .name("name")
                                                        .autoDelete(false)
                                                        .durable(true)
                                                        .exclusive(false)
                                                        .build())
                                                .build()))
                                .build(),
                        "exchange_routingKey",
                        ChannelObject.builder()
                                .channelId("exchange_routingKey")
                                .address("routingKey")
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPChannelBinding.builder()
                                                .is(AMQPChannelType.ROUTING_KEY)
                                                .exchange(AMQPChannelExchangeProperties.builder()
                                                        .name("exchange")
                                                        .build())
                                                .build()))
                                .build(),
                        "destination",
                        ChannelObject.builder()
                                .channelId("destination")
                                .address("destination")
                                .bindings(Map.of(
                                        "amqp",
                                        AMQPChannelBinding.builder()
                                                .is(AMQPChannelType.QUEUE)
                                                .queue(AMQPChannelQueueProperties.builder()
                                                        .name("destination")
                                                        .build())
                                                .build()))
                                .build()));
    }
}
