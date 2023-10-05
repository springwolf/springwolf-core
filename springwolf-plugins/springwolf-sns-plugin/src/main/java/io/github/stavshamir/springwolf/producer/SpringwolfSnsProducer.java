// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSnsConfigConstants.SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@Service
@ConditionalOnProperty(prefix = SPRINGWOLF_SNS_CONFIG_PREFIX, name = SPRINGWOLF_SNS_PLUGIN_PUBLISHING_ENABLED)
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
