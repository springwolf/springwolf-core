// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.producers;

import io.github.springwolf.bindings.stomp.annotations.StompAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import static io.github.springwolf.examples.stomp.config.Constants.ANOTHER_QUEUE;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnotherProducer {
    private final SimpMessageSendingOperations template;

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = "/app" + ANOTHER_QUEUE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    @StompAsyncOperationBinding
    public void sendMessage(AnotherPayloadDto msg) {
        log.debug("Publish to {}", ANOTHER_QUEUE);
        template.convertAndSend(ANOTHER_QUEUE, msg);
    }
}
