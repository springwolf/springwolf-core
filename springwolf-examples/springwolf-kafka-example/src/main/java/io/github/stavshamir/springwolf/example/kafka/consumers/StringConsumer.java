// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.AsyncApiPayload;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StringConsumer {
    private static final String TOPIC = "string-topic";

    @KafkaListener(topics = TOPIC)
    @AsyncListener(
            operation =
                    @AsyncOperation(
                            channelName = TOPIC,
                            description =
                                    "Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",
                            payloadType = StringEnvelope.class))
    @KafkaAsyncOperationBinding
    public void receiveStringPayload(String stringPayload) {
        log.info("Received new message in {}: {}", TOPIC, stringPayload);
    }

    @Data
    static class StringEnvelope {
        @AsyncApiPayload
        @Schema(
                description = "Payload description using @Schema annotation and @AsyncApiPayload within envelope class",
                maxLength = 100)
        private final String payload;
    }
}
