package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.Builder;

import java.util.Collections;

public class AmqpProducerData extends ProducerData {

    @Builder(builderMethodName = "amqpProducerDataBuilder")
    public AmqpProducerData(String queueName, String exchangeName, String routingKey, Class<?> payloadType, String description, AsyncHeaders headers) {
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

        this.messageBinding = ImmutableMap.of("amqp", AMQPMessageBinding.builder()
                .build());

        this.payloadType = payloadType;
        this.headers = headers != null ? headers : this.headers;
    }

}
