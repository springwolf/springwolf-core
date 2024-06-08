// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.stomp.consumers;

import io.github.springwolf.examples.stomp.stomp.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.stomp.stomp.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.stomp.stomp.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final String QUEUE = "/queue/example-queue";

    private final String SENDTO_QUEUE = "/queue/sendto-queue";
    private final String SENDTO_RESPONSE_QUEUE = "/topic/sendto-response-queue";

    private final String SENDTOUSER_QUEUE = "/queue/sendtouser-queue";
    private final String SENDTOUSER_RESPONSE_QUEUE = "/queue/sendtouser-response-queue";

    private final AnotherProducer anotherProducer;

    @MessageMapping(QUEUE)
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", QUEUE, payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @MessageMapping(SENDTO_QUEUE)
    @SendTo(SENDTO_RESPONSE_QUEUE)
    public ExamplePayloadDto receiveExamplePayloadSendTo(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", SENDTO_QUEUE, payload.toString());

        return payload;
    }

    @MessageMapping(SENDTOUSER_QUEUE)
    @SendToUser(SENDTOUSER_RESPONSE_QUEUE)
    public ExamplePayloadDto receiveExamplePayloadSendToUser(ExamplePayloadDto payload) {
        log.info("Received new message in {}: {}", SENDTOUSER_QUEUE, payload.toString());

        return payload;
    }
}
