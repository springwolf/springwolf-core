package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ExampleConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ExampleConsumer.class);

    @RabbitListener(queues = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        logger.info("Received new message in example-queue: {}", payload.toString());
    }

    @RabbitListener(queues = "another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        logger.info("Received new message in another-queue: {}", payload.toString());
    }

}
