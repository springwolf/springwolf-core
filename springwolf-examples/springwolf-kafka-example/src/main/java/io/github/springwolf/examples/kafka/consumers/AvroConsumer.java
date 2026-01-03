// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto;
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
    public void receiveExampleAvroPayload(AnotherPayloadAvroDto payloads) {
        log.debug("Received new message in avro-topic: {}", payloads.toString());
    }
}
