// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding;
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
                            payloadType = StringEnvelope.class,
                            headers = @AsyncOperation.Headers(notUsed = true)))
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
