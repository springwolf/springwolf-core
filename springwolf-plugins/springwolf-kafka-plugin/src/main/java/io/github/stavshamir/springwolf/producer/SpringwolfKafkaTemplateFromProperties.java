// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.github.stavshamir.springwolf.asyncapi.kafka.SpringwolfKafkaProducerConfiguration;
import io.github.stavshamir.springwolf.configuration.properties.SpringwolfKafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@ConditionalOnBean(value = SpringwolfKafkaProducerConfiguration.class)
public class SpringwolfKafkaTemplateFromProperties implements SpringwolfKafkaTemplateProvider {

    private final Optional<KafkaTemplate<Object, Object>> kafkaTemplate;

    public SpringwolfKafkaTemplateFromProperties(SpringwolfKafkaConfigProperties springWolfKafkaConfigProperties) {
        if (springWolfKafkaConfigProperties.getPublishing() != null
                && springWolfKafkaConfigProperties.getPublishing().getProducer() != null) {
            Map<String, Object> producerProperties = springWolfKafkaConfigProperties
                    .getPublishing()
                    .getProducer()
                    .buildProperties(new DefaultSslBundleRegistry());
            DefaultKafkaProducerFactory<Object, Object> producerFactory =
                    new DefaultKafkaProducerFactory<>(producerProperties);
            kafkaTemplate = Optional.of(new KafkaTemplate<>(producerFactory));
        } else {
            kafkaTemplate = Optional.empty();
        }
    }

    @Override
    public boolean isPresent() {
        return kafkaTemplate.isPresent();
    }

    @Override
    public Optional<KafkaTemplate<Object, Object>> get(String topic) {
        return kafkaTemplate;
    }
}
