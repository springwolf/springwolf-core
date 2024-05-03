// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.kafka.producers.AnotherProducer;
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
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

    @KafkaListener(topicPattern = "another-topic", groupId = "example-group-id", batch = "true")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }

    @AsyncListener(
            operation =
                    @AsyncOperation(
                            payloadType = LearningEvent.class,
                            description = "description",
                            channelName = "lms"))
    @KafkaAsyncOperationBinding
    @KafkaListener(topics = "lms", groupId = "test")
    public void receiveExamplePayload2(
            @Payload(required = false) List<ConsumerRecord<String, LearningEvent>> records,
            Acknowledgment acknowledgment) {
        log.info("Received new message in example-queue: {}", records.toString());
    }
}
