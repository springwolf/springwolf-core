// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.producers;

import io.github.springwolf.bindings.amqp.annotations.AmqpAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.amqp.AmqpConstants;
import io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnotherProducer {
    private final RabbitTemplate rabbitTemplate;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = AmqpConstants.EXCHANGE_EXAMPLE_TOPIC_EXCHANGE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @AmqpAsyncOperationBinding()
    public void sendMessage(AnotherPayloadDto msg) {
        // send
        rabbitTemplate.convertAndSend(
                AmqpConstants.EXCHANGE_EXAMPLE_TOPIC_EXCHANGE,
                AmqpConstants.ROUTING_KEY_EXAMPLE_TOPIC_ROUTING_KEY,
                msg);
    }
}
