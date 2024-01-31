// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.kafka.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
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

    @KafkaListener(topics = "example-topic")
    public void receiveExamplePayloadWithHeaders(
            @Headers MessageHeaders headers,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Payload(required = false) ExamplePayloadDto payload) {
        log.info("Received new messages in example-topic: {}", payload.toString());
    }

    @KafkaListener(topics = "another-topic", groupId = "example-group-id", batch = "true")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }
}
