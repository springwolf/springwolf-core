package io.github.stavshamir.springwolf.producer;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED;

@Slf4j
@Service
@ConditionalOnProperty(prefix = SPRINGWOLF_SQS_CONFIG_PREFIX, name = SPRINGWOLF_SQS_PLUGIN_PUBLISHING_ENABLED)
public class SpringwolfSqsProducer {

    private final Optional<SqsTemplate> template;

    public boolean isEnabled() {
        return template.isPresent();
    }

    public SpringwolfSqsProducer(List<SqsTemplate> templates) {
        this.template = templates.isEmpty() ? Optional.empty() : Optional.of(templates.get(0));
    }

    public void send(String channelName, Map<String, ?> payload) {
        if (template.isPresent()) {
            template.get().send(channelName, payload);
        } else {
            log.warn("SQS producer is not configured");
        }
    }
}
