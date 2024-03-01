// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.producers;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto;
import io.github.springwolf.plugins.amqp.asyncapi.annotations.AmqpAsyncOperationBinding;
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
                            channelName = "example-producer-channel-publisher",
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @AmqpAsyncOperationBinding()
    public void sendMessage(AnotherPayloadDto msg) {
        // send
        rabbitTemplate.convertAndSend("example-topic-exchange", "example-topic-routing-key", msg);
    }
}
