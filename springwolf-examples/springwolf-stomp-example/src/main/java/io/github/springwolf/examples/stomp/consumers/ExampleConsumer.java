// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.consumers;

import io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.stomp.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import static io.github.springwolf.examples.stomp.config.Constants.EXAMPLE_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTOUSER_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTOUSER_RESPONSE_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTO_QUEUE;
import static io.github.springwolf.examples.stomp.config.Constants.SENDTO_RESPONSE_QUEUE;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @MessageMapping(EXAMPLE_QUEUE)
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.debug("Received new message in {}: {}", EXAMPLE_QUEUE, payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @MessageMapping(SENDTO_QUEUE)
    @SendTo(SENDTO_RESPONSE_QUEUE)
    public ExamplePayloadDto receiveExamplePayloadSendTo(ExamplePayloadDto payload) {
        log.debug("Received new message in {}: {}", SENDTO_QUEUE, payload.toString());

        return payload;
    }

    @MessageMapping(SENDTOUSER_QUEUE)
    @SendToUser(SENDTOUSER_RESPONSE_QUEUE)
    public ExamplePayloadDto receiveExamplePayloadSendToUser(ExamplePayloadDto payload) {
        log.debug("Received new message in {}: {}", SENDTOUSER_QUEUE, payload.toString());

        return payload;
    }
}
