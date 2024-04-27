// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns.consumers;

import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.examples.sns.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.sns.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.sns.producers.AnotherProducer;
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
