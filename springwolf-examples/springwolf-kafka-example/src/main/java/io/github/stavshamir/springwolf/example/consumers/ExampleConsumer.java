package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ExampleConsumer.class);

    @KafkaListener(topics = "example-topic", containerFactory = "exampleKafkaListenerContainerFactory")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

    @KafkaListener(topics = "another-topic", containerFactory = "anotherKafkaListenerContainerFactory", groupId = "example-group-id")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        logger.info("Received new messages in another-topic: {}", payloads.toString());
    }

}
