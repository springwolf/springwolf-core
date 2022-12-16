package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExampleConsumer {

    @RabbitListener(queues = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
    }

    @RabbitListener(queues = "another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(name = "name", type = ExchangeTypes.TOPIC),
                    value = @Queue(name = "example-bindings-queue"))
    })
    public void bindingsExample(AnotherPayloadDto payload) {
        log.info("Received new message in example-bindings-queue: {}", payload.toString());
    }

    @RabbitListener(queues = "example-topic-queue")
    public void bindingsBeanExample(AnotherPayloadDto payload) {
        log.info("Received new message in example-topic-queue: {}", payload.toString());
    }
}
