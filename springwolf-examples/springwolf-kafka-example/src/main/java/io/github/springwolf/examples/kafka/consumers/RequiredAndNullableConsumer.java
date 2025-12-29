// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RequiredAndNullableConsumer {
    @KafkaListener(topics = "nullable-topic")
    @KafkaAsyncOperationBinding
    public void receiveNullablePayload(RequiredAndNullablePayloadDto payloads) {
        log.debug("Received new message in nullable-topic: {}", payloads.toString());
    }
}
