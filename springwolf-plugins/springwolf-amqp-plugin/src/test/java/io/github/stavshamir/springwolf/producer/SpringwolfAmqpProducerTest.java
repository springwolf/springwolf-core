// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SpringwolfAmqpProducerTest {
    private SpringwolfAmqpProducer springwolfAmqpProducer;

    private AsyncApiService asyncApiService;
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        asyncApiService = mock(AsyncApiService.class);
        rabbitTemplate = mock(RabbitTemplate.class);

        springwolfAmqpProducer = new SpringwolfAmqpProducer(asyncApiService, List.of(rabbitTemplate));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(new Info())
                .channels(Map.of("channel-name", new ChannelObject()))
                .build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq(""), eq("channel-name"), same(payload));
    }

    @Test
    void send_exchangeAndNoRoutingKey() {
        AMQPChannelExchangeProperties properties = new AMQPChannelExchangeProperties();
        properties.setName("exchange-name");
        ChannelObject channelItem = ChannelObject.builder()
                .bindings(Map.of(
                        "amqp",
                        AMQPChannelBinding.builder().exchange(properties).build()))
                .build();
        Map<String, ChannelObject> channels = Map.of("channel-name", channelItem);
        Operation operation = Operation.builder()
                .action(OperationAction.SEND)
                .bindings(Map.of("amqp", AMQPOperationBinding.builder().build()))
                .channel(ChannelReference.fromChannel("channel-name"))
                .build();
        Map<String, Operation> operations = Map.of("amqp", operation);

        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(new Info())
                .channels(channels)
                .operations(operations)
                .build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq(""), same(payload));
    }

    @Test
    void send_exchangeAndRoutingKeyFromBindings() {
        AMQPChannelExchangeProperties properties = new AMQPChannelExchangeProperties();
        properties.setName("exchange-name");
        ChannelObject channelItem = ChannelObject.builder()
                .bindings(Map.of(
                        "amqp",
                        AMQPChannelBinding.builder().exchange(properties).build()))
                .build();
        Map<String, ChannelObject> channels = Map.of("channel-name", channelItem);
        Operation operation = Operation.builder()
                .bindings(Map.of(
                        "amqp",
                        AMQPOperationBinding.builder()
                                .cc(List.of("routing-key"))
                                .build()))
                .build();
        Map<String, Operation> operations = Map.of("amqp", operation);

        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(new Info())
                .channels(channels)
                .operations(operations)
                .build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq("routing-key"), same(payload));
    }
}
