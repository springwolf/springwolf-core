// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.github.springwolf.core.controller.dtos.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void send(String channelName, Map<String, MessageDto.HeaderValue> headers, Object payload) {
        if (template.isPresent()) {
            Message<?> message = MessageBuilder.withPayload(payload)
                    .copyHeaders(mapHeaders(headers))
                    .build();
            template.get().send(channelName, message);
        } else {
            log.warn("SQS producer is not configured");
        }
    }

    private static Map<String, String> mapHeaders(Map<String, MessageDto.HeaderValue> headers) {
        return headers.entrySet().stream()
                .collect(
                        HashMap::new,
                        (m, e) -> m.put(
                                e.getKey(),
                                e.getValue() == null ? null : e.getValue().stringValue()),
                        HashMap::putAll);
    }
}
