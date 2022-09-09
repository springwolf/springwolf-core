package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.Builder;

import java.util.Collections;

public class AmqpConsumerData extends ConsumerData {

    @Builder(builderMethodName = "amqpConsumerDataBuilder")
    public AmqpConsumerData(String queueName, String exchangeName, String routingKey, Class<?> payloadType, String description) {
        this.channelName = queueName;
        this.description = description;

        AMQPChannelBinding.ExchangeProperties exchangeProperties = new AMQPChannelBinding.ExchangeProperties();
        exchangeProperties.setName(exchangeName);
        this.channelBinding = ImmutableMap.of("amqp", AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(exchangeProperties)
                .build());

        this.operationBinding = ImmutableMap.of("amqp", AMQPOperationBinding.builder()
                .cc(Collections.singletonList(routingKey))
                .build());

        this.payloadType = payloadType;
    }

}
