// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SpringwolfJmsProducer {

    private final Optional<JmsTemplate> template;

    public SpringwolfJmsProducer(List<JmsTemplate> templates) {
        this.template = templates.isEmpty() ? Optional.empty() : Optional.of(templates.get(0));
    }

    public boolean isEnabled() {
        return template.isPresent();
    }

    public void send(String channelName, Object payload) {
        if (template.isPresent()) {
            template.get().convertAndSend(channelName, payload);
        } else {
            log.warn("JMS producer is not configured");
        }
    }
}
