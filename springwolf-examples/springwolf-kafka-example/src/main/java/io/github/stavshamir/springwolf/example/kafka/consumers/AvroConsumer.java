// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.example.kafka.dto.avro.ExamplePayloadAvroDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AvroConsumer {
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
    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = "avro-topic",
                            description =
                                    "Requires a running kafka-schema-registry. See docker-compose.yml to start it"))
    @KafkaAsyncOperationBinding
    public void receiveExampleAvroPayload(ExamplePayloadAvroDto payloads) {
        log.info("Received new message in avro-topic: {}", payloads.toString());
    }
}
