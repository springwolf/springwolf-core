package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.Builder;

import java.util.Collections;
import java.util.Map;

public class AmqpProducerData extends ProducerData {

    @Builder(builderMethodName = "amqpProducerDataBuilder")
    public AmqpProducerData(
            String queueName,
            String exchangeName,
            String routingKey,
            Class<?> payloadType,
            String description,
            AsyncHeaders headers) {
        this.channelName = queueName;
        this.description = description;

        AMQPChannelBinding.ExchangeProperties exchangeProperties = new AMQPChannelBinding.ExchangeProperties();
        exchangeProperties.setName(exchangeName);
        this.channelBinding = Map.of(
                "amqp",
                AMQPChannelBinding.builder()
                        .is("routingKey")
                        .exchange(exchangeProperties)
                        .build());

        this.operationBinding = Map.of(
                "amqp",
                AMQPOperationBinding.builder()
                        .cc(Collections.singletonList(routingKey))
                        .build());

        this.messageBinding = Map.of("amqp", AMQPMessageBinding.builder().build());

        this.payloadType = payloadType;
        this.headers = headers != null ? headers : this.headers;
    }
}
