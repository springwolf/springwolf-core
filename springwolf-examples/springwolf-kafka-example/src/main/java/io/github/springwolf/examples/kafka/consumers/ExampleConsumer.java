// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.kafka.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @KafkaListener(topics = "example-topic")
    public void receiveExamplePayload(
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.OFFSET) Integer offset,
            @Header(KafkaHeaders.RECORD_METADATA) ConsumerRecordMetadata meta,
            @Payload ExamplePayloadDto payload) {
        log.debug("Received new message in example-topic: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @KafkaListener(topicPattern = "another-topic", groupId = "example-group-id", batch = "true")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.debug("Received new message in another-topic: {}", payloads.toString());
    }
}
