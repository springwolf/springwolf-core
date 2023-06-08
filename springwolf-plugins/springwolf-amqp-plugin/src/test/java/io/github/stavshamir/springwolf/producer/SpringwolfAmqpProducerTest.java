package io.github.stavshamir.springwolf.producer;

import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import com.asyncapi.v2._0_0.model.channel.ChannelItem;
import com.asyncapi.v2._0_0.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.*;

class SpringwolfAmqpProducerTest {
    private SpringwolfAmqpProducer springwolfAmqpProducer;

    private ChannelsService channelsService;
    private RabbitTemplate rabbitTemplate;

    @BeforeEach
    void setUp() {
        channelsService = mock(ChannelsService.class);
        rabbitTemplate = mock(RabbitTemplate.class);

        springwolfAmqpProducer = new SpringwolfAmqpProducer(channelsService, Collections.singletonList(rabbitTemplate));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        when(channelsService.getChannels()).thenReturn(Map.of("channel-name", new ChannelItem()));

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq(""), eq("channel-name"), same(payload));
    }

    @Test
    void send_exchangeAndNoRoutingKey() {
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("exchange-name");
        ChannelItem channelItem = ChannelItem.builder()
                .bindings(Map.of("amqp", AMQPChannelBinding.builder()
                        .exchange(properties)
                        .build()))
                .publish(Operation.builder()
                        .bindings(Map.of("amqp", new AMQPOperationBinding()))
                        .build())
                .build();
        Map<String, ChannelItem> channels = Map.of("channel-name", channelItem);
        when(channelsService.getChannels()).thenReturn(channels);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq(""), same(payload));
    }

    @Test
    void send_exchangeAndRoutingKeyFromBindings() {
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("exchange-name");
        ChannelItem channelItem = ChannelItem.builder()
                .bindings(Map.of("amqp", AMQPChannelBinding.builder()
                        .exchange(properties)
                        .build()))
                .publish(Operation.builder()
                        .bindings(Map.of("amqp", AMQPOperationBinding.builder()
                                .cc(Collections.singletonList("routing-key"))
                                .build()))
                        .build())
                .build();
        Map<String, ChannelItem> channels = Map.of("channel-name", channelItem);
        when(channelsService.getChannels()).thenReturn(channels);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq("routing-key"), same(payload));
    }
}
