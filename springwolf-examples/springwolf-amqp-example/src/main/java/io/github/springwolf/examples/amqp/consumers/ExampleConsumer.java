// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.consumers;

import io.github.springwolf.examples.amqp.AmqpConstants;
import io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.amqp.dtos.GenericPayloadDto;
import io.github.springwolf.examples.amqp.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @RabbitListener(queues = AmqpConstants.QUEUE_EXAMPLE_QUEUE)
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", AmqpConstants.QUEUE_EXAMPLE_QUEUE, payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @RabbitListener(queues = AmqpConstants.QUEUE_ANOTHER_QUEUE)
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in {}: {}", AmqpConstants.QUEUE_ANOTHER_QUEUE, payload.toString());
    }

    @RabbitListener(
            bindings = {
                @QueueBinding(
                        exchange =
                                @Exchange(
                                        name = AmqpConstants.EXCHANGE_EXAMPLE_TOPIC_EXCHANGE,
                                        type = ExchangeTypes.TOPIC),
                        value =
                                @Queue(
                                        name = AmqpConstants.QUEUE_EXAMPLE_BINDINGS_QUEUE,
                                        durable = "false",
                                        exclusive = "false",
                                        autoDelete = "true"),
                        key = AmqpConstants.ROUTING_KEY_EXAMPLE_TOPIC_ROUTING_KEY)
            })
    public void bindingsExample(AnotherPayloadDto payload) {
        log.info(
                "Received new message in {}" + " through exchange {}" + " using routing key {}: {}",
                AmqpConstants.QUEUE_EXAMPLE_BINDINGS_QUEUE,
                AmqpConstants.EXCHANGE_EXAMPLE_TOPIC_EXCHANGE,
                AmqpConstants.ROUTING_KEY_EXAMPLE_TOPIC_ROUTING_KEY,
                payload.toString());
    }

    @RabbitListener(queues = AmqpConstants.QUEUE_MULTI_PAYLOAD_QUEUE)
    public void bindingsBeanExample(AnotherPayloadDto payload) {
        log.info(
                "Received new message in {} (AnotherPayloadDto): {}",
                AmqpConstants.QUEUE_MULTI_PAYLOAD_QUEUE,
                payload.toString());
    }

    @RabbitListener(queues = AmqpConstants.QUEUE_MULTI_PAYLOAD_QUEUE)
    public void bindingsBeanExample(ExamplePayloadDto payload) {
        log.info(
                "Received new message in {} (ExamplePayloadDto): {}",
                AmqpConstants.QUEUE_MULTI_PAYLOAD_QUEUE,
                payload.toString());
    }

    @RabbitListener(queuesToDeclare = @Queue(name = AmqpConstants.QUEUE_CREATE, autoDelete = "false", durable = "true"))
    public void queuesToDeclareCreate(Message message, @Payload GenericPayloadDto<String> payload) {
        log.info(
                "Received new message {} in {} (GenericPayloadDto<String>): {}",
                message,
                AmqpConstants.QUEUE_CREATE,
                payload.toString());
    }

    @RabbitListener(queuesToDeclare = @Queue(name = AmqpConstants.QUEUE_DELETE, autoDelete = "false", durable = "true"))
    public void queuesToDeclareDelete(Message message, @Payload GenericPayloadDto<Long> payload) {
        log.info(
                "Received new message {} in {} (GenericPayloadDto<Long>): {}",
                message,
                AmqpConstants.QUEUE_DELETE,
                payload.toString());
    }

    @RabbitListener(
            autoStartup = "false",
            bindings =
                    @QueueBinding(
                            exchange =
                                    @Exchange(
                                            name = AmqpConstants.EXCHANGE_CRUD_TOPIC_EXCHANGE_1,
                                            type = ExchangeTypes.TOPIC),
                            key = AmqpConstants.ROUTING_KEY_ALL_MESSAGES,
                            value = @Queue(name = AmqpConstants.QUEUE_UPDATE, durable = "true", autoDelete = "false")))
    public void bindingsUpdate(Message message, @Payload GenericPayloadDto<ExamplePayloadDto> payload) {
        log.info(
                "Received new message {} in {} (GenericPayloadDto<ExamplePayloadDto>): {}",
                message,
                AmqpConstants.QUEUE_UPDATE,
                payload.toString());
    }

    @RabbitListener(
            autoStartup = "false",
            bindings =
                    @QueueBinding(
                            exchange =
                                    @Exchange(
                                            name = AmqpConstants.EXCHANGE_CRUD_TOPIC_EXCHANGE_2,
                                            type = ExchangeTypes.TOPIC),
                            key = AmqpConstants.ROUTING_KEY_ALL_MESSAGES,
                            value = @Queue(name = AmqpConstants.QUEUE_READ, durable = "false", autoDelete = "false")))
    public void bindingsRead(Message message, @Payload ExamplePayloadDto payload) {
        log.info(
                "Received new message {} in {} (ExamplePayloadDto): {}",
                message,
                AmqpConstants.QUEUE_UPDATE,
                payload.toString());
    }
}
