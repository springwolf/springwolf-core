package io.github.stavshamir.springwolf.producer;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2._6_0.model.info.Info;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.types.AsyncAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Collections;
import java.util.HashMap;
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

        springwolfAmqpProducer = new SpringwolfAmqpProducer(asyncApiService, Collections.singletonList(rabbitTemplate));
    }

    @Test
    void send_defaultExchangeAndChannelNameAsRoutingKey() {
        AsyncAPI asyncAPI = AsyncAPI.builder()
                .info(new Info())
                .channels(Map.of("channel-name", new ChannelItem()))
                .build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq(""), eq("channel-name"), same(payload));
    }

    @Test
    void send_exchangeAndNoRoutingKey() {
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("exchange-name");
        ChannelItem channelItem = ChannelItem.builder()
                .bindings(Map.of(
                        "amqp",
                        AMQPChannelBinding.builder().exchange(properties).build()))
                .publish(Operation.builder()
                        .bindings(Map.of("amqp", new AMQPOperationBinding()))
                        .build())
                .build();
        Map<String, ChannelItem> channels = Map.of("channel-name", channelItem);

        AsyncAPI asyncAPI =
                AsyncAPI.builder().info(new Info()).channels(channels).build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq(""), same(payload));
    }

    @Test
    void send_exchangeAndRoutingKeyFromBindings() {
        AMQPChannelBinding.ExchangeProperties properties = new AMQPChannelBinding.ExchangeProperties();
        properties.setName("exchange-name");
        ChannelItem channelItem = ChannelItem.builder()
                .bindings(Map.of(
                        "amqp",
                        AMQPChannelBinding.builder().exchange(properties).build()))
                .publish(Operation.builder()
                        .bindings(Map.of(
                                "amqp",
                                AMQPOperationBinding.builder()
                                        .cc(Collections.singletonList("routing-key"))
                                        .build()))
                        .build())
                .build();
        Map<String, ChannelItem> channels = Map.of("channel-name", channelItem);

        AsyncAPI asyncAPI =
                AsyncAPI.builder().info(new Info()).channels(channels).build();
        when(asyncApiService.getAsyncAPI()).thenReturn(asyncAPI);

        Map<String, Object> payload = new HashMap<>();
        springwolfAmqpProducer.send("channel-name", payload);

        verify(rabbitTemplate).convertAndSend(eq("exchange-name"), eq("routing-key"), same(payload));
    }
}
