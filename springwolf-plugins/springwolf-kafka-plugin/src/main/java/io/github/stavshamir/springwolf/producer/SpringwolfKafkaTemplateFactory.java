// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.github.stavshamir.springwolf.asyncapi.kafka.SpringwolfKafkaProducerConfiguration;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@ConditionalOnBean(value = SpringwolfKafkaProducerConfiguration.class)
public class SpringwolfKafkaTemplateFactory {

    private final SpringwolfKafkaConfigProperties springWolfKafkaConfigProperties;

    public Optional<KafkaTemplate<Object, Object>> buildKafkaTemplate() {
        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = Optional.empty();

        if (springWolfKafkaConfigProperties.getPublishing() != null
                && springWolfKafkaConfigProperties.getPublishing().getProducer() != null) {
            Map<String, Object> producerProperties = springWolfKafkaConfigProperties
                    .getPublishing()
                    .getProducer()
                    .buildProperties();
            DefaultKafkaProducerFactory<Object, Object> producerFactory =
                    new DefaultKafkaProducerFactory<>(producerProperties);
            kafkaTemplate = Optional.of(new KafkaTemplate<>(producerFactory));
        }

        return kafkaTemplate;
    }
}
