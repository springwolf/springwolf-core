package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.channel.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import lombok.Builder;

public class KafkaConsumerData extends ConsumerData {
    @Builder(builderMethodName = "kafkaConsumerDataBuilder")
    public KafkaConsumerData(String topicName, Class<?> payloadType, String description, AsyncHeaders headers) {
        this.channelName = topicName;
        this.description = description;
        this.channelBinding = ImmutableMap.of("kafka", new KafkaChannelBinding());
        this.payloadType = payloadType;
        this.headers = headers != null ? headers : this.headers;
        this.operationBinding = ImmutableMap.of("kafka", new KafkaOperationBinding());
        this.messageBinding = ImmutableMap.of("kafka", new KafkaMessageBinding());
    }
}
