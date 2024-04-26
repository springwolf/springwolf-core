// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IntegerConsumer {
    private static final String TOPIC = "integer-topic";

    @KafkaListener(topics = TOPIC)
    public void receiveIntegerPayload(Integer integerPayload) {
        log.info("Received new message in {}: {}", TOPIC, integerPayload);
    }
}
