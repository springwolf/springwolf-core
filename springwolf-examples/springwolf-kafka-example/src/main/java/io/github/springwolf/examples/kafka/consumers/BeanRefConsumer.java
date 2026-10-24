// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.kafka.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeanRefConsumer {

    private final String topic = "bean-ref-topic";
    private final String group = "bean-ref-group";

    @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.group}")
    public void listen(Object message) {
        log.debug("Received new message in {}: {}", this.topic, message.toString());
    }

    public String getTopic() {
        return this.topic;
    }

    public String getGroup() {
        return this.group;
    }
}
