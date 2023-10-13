// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SpringwolfSnsProducer {

    private final Optional<SnsTemplate> template;

    public SpringwolfSnsProducer(List<SnsTemplate> templates) {
        this.template = templates.isEmpty() ? Optional.empty() : Optional.of(templates.get(0));
    }

    public boolean isEnabled() {
        return template.isPresent();
    }

    public void send(String channelName, Message<?> payload) {
        if (template.isPresent()) {
            template.get().send(channelName, payload);
        } else {
            log.warn("SNS producer is not configured");
        }
    }
}
