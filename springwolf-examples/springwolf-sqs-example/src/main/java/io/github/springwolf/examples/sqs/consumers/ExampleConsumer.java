// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs.consumers;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.sqs.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    @SqsListener("example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @SqsListener("another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }
}
