package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "multi-payload-topic", containerFactory = "exampleKafkaListenerContainerFactory")
public class ExampleClassLevelKafkaListener {

    private static final Logger logger = LoggerFactory.getLogger(ExampleClassLevelKafkaListener.class);

    @KafkaHandler
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

    @KafkaHandler
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        logger.info("Received new message in another-topic: {}", payload.toString());
    }

}
