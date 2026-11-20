// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.publish;

import io.github.springwolf.core.controller.publish.dtos.MessageDto;
import jakarta.jms.JMSException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class SpringwolfJmsPublisher {

    private final Optional<JmsTemplate> template;

    public SpringwolfJmsPublisher(List<JmsTemplate> templates) {
        this.template = templates.isEmpty() ? Optional.empty() : Optional.of(templates.get(0));
    }

    public boolean isEnabled() {
        return template.isPresent();
    }

    public void send(String channelName, Map<String, MessageDto.HeaderValue> headers, Object payload) {
        if (template.isPresent()) {
            template.get().convertAndSend(channelName, payload, message -> {
                if (headers != null) {
                    headers.forEach((name, value) -> {
                        try {
                            message.setStringProperty(name, value.stringValue());
                        } catch (JMSException ex) {
                            log.warn("Unable to set JMS Header key={} value={}", name, value, ex);
                        }
                    });
                }
                return message;
            });

        } else {
            log.warn("JmsTemplate is not configured");
        }
    }
}
