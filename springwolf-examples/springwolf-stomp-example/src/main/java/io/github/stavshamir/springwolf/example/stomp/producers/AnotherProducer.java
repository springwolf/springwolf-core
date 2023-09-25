package io.github.stavshamir.springwolf.example.stomp.producers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.example.stomp.dtos.AnotherPayloadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AnotherProducer {
    private final SimpMessageSendingOperations template;

    public static final String QUEUE = "/queue/another-queue";

    @AsyncPublisher(
            operation =
                    @AsyncOperation(
                            channelName = QUEUE,
                            description = "Custom, optional description defined in the AsyncPublisher annotation"))
    //    @StompAsyncOperationBinding // TODO:
    public void sendMessage(AnotherPayloadDto msg) {
        log.debug("Publish to {}", QUEUE);
        template.convertAndSend(QUEUE, msg);
    }
}
