// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.core.asyncapi.types.ConsumerData;
import io.github.springwolf.core.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.Builder;

import java.util.Map;

@Deprecated(forRemoval = true)
public class KafkaConsumerData extends ConsumerData {
    @Builder(builderMethodName = "kafkaConsumerDataBuilder")
    public KafkaConsumerData(String topicName, Class<?> payloadType, String description, AsyncHeaders headers) {
        this.channelName = topicName;
        this.description = description;
        this.channelBinding = Map.of("kafka", new KafkaChannelBinding());
        this.payloadType = payloadType;
        this.headers = headers != null ? headers : this.headers;
        this.operationBinding = Map.of("kafka", new KafkaOperationBinding());
        this.messageBinding = Map.of("kafka", new KafkaMessageBinding());
    }
}
