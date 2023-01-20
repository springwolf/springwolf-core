package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExampleConsumer {

    @KafkaListener(topics = "example-topic", containerFactory = "exampleKafkaListenerContainerFactory")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-topic: {}", payload.toString());
    }

    @KafkaListener(topics = "another-topic", containerFactory = "anotherKafkaListenerContainerFactory", groupId = "example-group-id")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }

}
