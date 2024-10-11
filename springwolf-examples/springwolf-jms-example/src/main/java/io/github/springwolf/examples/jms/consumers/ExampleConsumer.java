// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms.consumers;

import io.github.springwolf.examples.jms.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.jms.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.jms.producers.AnotherProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    public ExampleConsumer(AnotherProducer anotherProducer) { // TODO: remove
        this.anotherProducer = anotherProducer;

        log.info("Created ExampleConsumer: {}", this);
    }

    @JmsListener(destination = "example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue {}: {}", this, payload.toString()); // TODO: remove (this)

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
