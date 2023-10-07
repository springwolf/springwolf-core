// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;

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

    public void send(String channelName, Object payload) {
        if (template.isPresent()) {
            template.get().send(channelName, payload);
        } else {
            log.warn("SQS producer is not configured");
        }
    }
}
