// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.example.kafka.dto.avro.ExamplePayloadAvroDto;
import io.github.stavshamir.springwolf.example.kafka.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.kafka.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @KafkaListener(topics = "example-topic")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @KafkaListener(topics = "another-topic", groupId = "example-group-id", batch = "true")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }

    @KafkaListener(
            topics = "avro-topic",
            properties = {
                "specific.avro.reader=true",
                "schema.registry.url=${KAFKA_SCHEMA_REGISTRY_URL:http://localhost:8081}",
                "key.deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer",
                "value.deserializer=org.springframework.kafka.support.serializer.ErrorHandlingDeserializer",
                "spring.deserializer.key.delegate.class=org.apache.kafka.common.serialization.StringDeserializer",
                "spring.deserializer.value.delegate.class=io.confluent.kafka.serializers.KafkaAvroDeserializer"
            })
    public void receiveExampleAvroPayload(List<ExamplePayloadAvroDto> payloads) {
        log.info("Received new message in avro-topic: {}", payloads.toString());
    }
}
