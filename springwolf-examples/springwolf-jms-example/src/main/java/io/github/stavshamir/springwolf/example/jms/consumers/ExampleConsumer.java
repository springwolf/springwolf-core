// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.jms.consumers;

import io.github.stavshamir.springwolf.example.jms.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.jms.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.jms.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    @JmsListener(destination = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @JmsListener(destination = "another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }
}
