// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.sns.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBindingIdentifier;
import io.github.stavshamir.springwolf.example.sns.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.sns.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.sns.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    @AsyncListener(operation = @AsyncOperation(channelName = "example-topic"))
    @SnsAsyncOperationBinding(protocol = "sqs", endpoint = @SnsAsyncOperationBindingIdentifier())
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-topic: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @AsyncListener(operation = @AsyncOperation(channelName = "another-topic"))
    @SnsAsyncOperationBinding(protocol = "sqs", endpoint = @SnsAsyncOperationBindingIdentifier())
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-topic: {}", payload.toString());
    }
}
