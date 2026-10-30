// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Getter
public class BeanRefConsumer {

    private final String topic = "bean-ref-topic";
    private final String group = "bean-ref-group";

    @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.group}")
    public void listen(Object message) {
        log.debug("Received new message in {}: {}", this.topic, message.toString());
    }
}
