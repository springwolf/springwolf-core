// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.Builder;

import java.util.Map;

@Deprecated(forRemoval = true)
public class KafkaProducerData extends ProducerData {

    @Builder(builderMethodName = "kafkaProducerDataBuilder")
    public KafkaProducerData(String topicName, Class<?> payloadType, String description, AsyncHeaders headers) {
        this.channelName = topicName;
        this.description = description;
        this.channelBinding = Map.of("kafka", new KafkaChannelBinding());
        this.payloadType = payloadType;
        this.headers = headers != null ? headers : this.headers;
        this.operationBinding = Map.of("kafka", new KafkaOperationBinding());
        this.messageBinding = Map.of("kafka", new KafkaMessageBinding());
    }
}
