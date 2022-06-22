package io.github.stavshamir.springwolf.asyncapi.types;

import com.asyncapi.v2.binding.kafka.KafkaChannelBinding;
import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import com.google.common.collect.ImmutableMap;
import lombok.Builder;

public class KafkaConsumerData extends ConsumerData {
    @Builder(builderMethodName = "kafkaConsumerDataBuilder")
    public KafkaConsumerData(String topicName, Class<?> payloadType) {
        this.channelName = topicName;
        this.channelBinding = ImmutableMap.of("kafka", new KafkaChannelBinding());
        this.payloadType = payloadType;
        this.operationBinding = ImmutableMap.of("kafka", new KafkaOperationBinding());
    }
}
