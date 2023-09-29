// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp.consumers;

import io.github.stavshamir.springwolf.example.amqp.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.amqp.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.amqp.producers.ExampleProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleConsumer {

    private final ExampleProducer exampleProducer;

    @RabbitListener(queues = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());
        exampleProducer.sendMessage(payload);
    }

    @RabbitListener(queues = "another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }

    @RabbitListener(
            bindings = {
                @QueueBinding(
                        exchange = @Exchange(name = "example-bindings-exchange-name", type = ExchangeTypes.TOPIC),
                        value =
                                @Queue(
                                        name = "example-bindings-queue",
                                        durable = "false",
                                        exclusive = "true",
                                        autoDelete = "true"),
                        key = "example-topic-routing-key")
            })
    public void bindingsExample(AnotherPayloadDto payload) {
        log.info("Received new message in example-bindings-queue: {}", payload.toString());
    }

    @RabbitListener(queues = "example-topic-queue")
    public void bindingsBeanExample(AnotherPayloadDto payload) {
        log.info("Received new message in example-topic-queue: {}", payload.toString());
    }
}
