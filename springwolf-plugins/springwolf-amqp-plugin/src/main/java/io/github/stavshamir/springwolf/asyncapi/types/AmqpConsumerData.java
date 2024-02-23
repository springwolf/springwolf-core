// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types;

import io.github.springwolf.core.asyncapi.types.ConsumerData;
import io.github.springwolf.core.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelType;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import lombok.Builder;

import java.util.Collections;
import java.util.Map;

@Deprecated(forRemoval = true)
public class AmqpConsumerData extends ConsumerData {

    @Builder(builderMethodName = "amqpConsumerDataBuilder")
    public AmqpConsumerData(
            String queueName,
            String exchangeName,
            String routingKey,
            Class<?> payloadType,
            String description,
            AsyncHeaders headers) {
        this.channelName = queueName;
        this.description = description;

        AMQPChannelExchangeProperties exchangeProperties = new AMQPChannelExchangeProperties();
        exchangeProperties.setName(exchangeName);
        this.channelBinding = Map.of(
                "amqp",
                AMQPChannelBinding.builder()
                        .is(AMQPChannelType.ROUTING_KEY)
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
