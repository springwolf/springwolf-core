// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp.producers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.example.amqp.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.amqp.dtos.ExamplePayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExampleProducer {
    private final RabbitTemplate rabbitTemplate;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "example-producer-channel-publisher",
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @AmqpAsyncOperationBinding()
    public void sendMessage(ExamplePayloadDto msg) {
        // send
        AnotherPayloadDto dto = new AnotherPayloadDto("fooValue", msg);
        rabbitTemplate.convertAndSend("example-topic-exchange", "example-topic-routing-key", dto);
    }
}
