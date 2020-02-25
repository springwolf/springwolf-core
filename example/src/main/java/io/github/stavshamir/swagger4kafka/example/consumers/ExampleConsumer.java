package io.github.stavshamir.swagger4kafka.example.consumers;

import io.github.stavshamir.swagger4kafka.example.dtos.ExamplePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExampleConsumer {

    private static Logger logger = LoggerFactory.getLogger(ExampleConsumer.class);

    @KafkaListener(topics = "example-topic", containerFactory = "exampleKafkaListenerContainerFactory")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        logger.info("Received new message in example-topic: {}", payload.toString());
    }

}
