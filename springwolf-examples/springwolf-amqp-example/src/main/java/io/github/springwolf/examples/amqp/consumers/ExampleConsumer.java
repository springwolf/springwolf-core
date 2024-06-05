// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.consumers;

import io.github.springwolf.examples.amqp.AmqpConstants;
import io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.amqp.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @RabbitListener(queues = AmqpConstants.EXAMPLE_QUEUE)
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", AmqpConstants.EXAMPLE_QUEUE, payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @RabbitListener(queues = AmqpConstants.ANOTHER_QUEUE)
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in {}: {}", AmqpConstants.ANOTHER_QUEUE, payload.toString());
    }

    @RabbitListener(
            bindings = {
                @QueueBinding(
                        exchange = @Exchange(name = AmqpConstants.EXAMPLE_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC),
                        value =
                                @Queue(
                                        name = AmqpConstants.EXAMPLE_BINDINGS_QUEUE,
                                        durable = "false",
                                        exclusive = "true",
                                        autoDelete = "true"),
                        key = AmqpConstants.EXAMPLE_TOPIC_ROUTING_KEY)
            })
    public void bindingsExample(AnotherPayloadDto payload) {
        log.info(
                "Received new message in {}" + " through exchange {}" + " using routing key {}: {}",
                AmqpConstants.EXAMPLE_BINDINGS_QUEUE,
                AmqpConstants.EXAMPLE_TOPIC_EXCHANGE,
                AmqpConstants.EXAMPLE_TOPIC_ROUTING_KEY,
                payload.toString());
    }

    @RabbitListener(queues = AmqpConstants.MULTI_PAYLOAD_QUEUE)
    public void bindingsBeanExample(AnotherPayloadDto payload) {
        log.info(
                "Received new message in {} (AnotherPayloadDto): {}",
                AmqpConstants.MULTI_PAYLOAD_QUEUE,
                payload.toString());
    }

    @RabbitListener(queues = AmqpConstants.MULTI_PAYLOAD_QUEUE)
    public void bindingsBeanExample(ExamplePayloadDto payload) {
        log.info(
                "Received new message in {} (ExamplePayloadDto): {}",
                AmqpConstants.MULTI_PAYLOAD_QUEUE,
                payload.toString());
    }
}
