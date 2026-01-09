// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoPayloadUsedConsumer {

    @KafkaListener(topics = "no-payload-used-topic")
    public void receiveExamplePayload() {
        log.debug("Received new message in no-payload-used-topic");
    }
}
