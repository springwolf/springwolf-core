// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncApiPayload;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
                            description =
                                    "Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",
                            payloadType = StringEnvelope.class,
                            message = @AsyncMessage(name = "StringPayload"),
                            headers = @AsyncOperation.Headers(notUsed = true)))
    @KafkaAsyncOperationBinding
    public void receiveStringPayload(String stringPayload) {
        log.debug("Received new message in {}: {}", TOPIC, stringPayload);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class StringEnvelope {
        @AsyncApiPayload
        @Schema(
                description = "Payload description using @Schema annotation and @AsyncApiPayload within envelope class",
                maxLength = 100)
        private String payload;
    }
}
