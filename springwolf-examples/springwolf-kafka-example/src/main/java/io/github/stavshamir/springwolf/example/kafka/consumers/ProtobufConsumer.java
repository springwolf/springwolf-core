// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.example.kafka.dto.proto.ExamplePayloadProtobufDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProtobufConsumer {

    @KafkaListener(
            topics = "protobuf-topic",
            properties = {
                "derive.type=true",
                "schema.registry.url=${KAFKA_SCHEMA_REGISTRY_URL:http://localhost:8081}",
                "key.deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer",
                "value.deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer",
                "spring.deserializer.key.delegate.class=org.apache.kafka.common.serialization.StringDeserializer",
                "spring.deserializer.value.delegate.class=io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer"
            })
    public void receiveExampleProtobufPayload(ExamplePayloadProtobufDto.Message payload) {
        log.info("Received new message in protobuf-topic: {}", payload.toString());
    }
}
