// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SpringwolfSqsProducer {

    private final Optional<SqsTemplate> template;

    public SpringwolfSqsProducer(List<SqsTemplate> templates) {
        this.template = templates.isEmpty() ? Optional.empty() : Optional.of(templates.get(0));
    }

    public boolean isEnabled() {
        return template.isPresent();
    }

    public void send(String channelName, Message<?> message) {
        if (template.isPresent()) {
            template.get().send(channelName, message);
        } else {
            log.warn("SQS producer is not configured");
        }
    }
}
