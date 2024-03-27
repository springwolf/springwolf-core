// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;

public interface SpringwolfKafkaTemplateProvider {
    /**
     * Check if publishing in general is possible
     *
     * @return true if at least in one case a kafka template is present
     */
    boolean isPresent();

    /**
     * Returns the matching kafka template for the topic
     */
    Optional<KafkaTemplate<Object, Object>> get(String topic);
}
