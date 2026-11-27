// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.producer;

import io.github.springwolf.plugins.kafka.configuration.SpringwolfKafkaPublisherConfiguration;
import io.github.springwolf.plugins.kafka.configuration.properties.SpringwolfKafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.kafka.autoconfigure.KafkaConfigBuilder;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@ConditionalOnBean(value = SpringwolfKafkaPublisherConfiguration.class)
public class SpringwolfKafkaTemplateFromProperties implements SpringwolfKafkaTemplateProvider {

    private final Optional<KafkaTemplate<Object, Object>> kafkaTemplate;

    public SpringwolfKafkaTemplateFromProperties(SpringwolfKafkaConfigProperties springWolfKafkaConfigProperties) {
        if (springWolfKafkaConfigProperties.getPublishing() != null
                && springWolfKafkaConfigProperties.getPublishing().getProducer() != null) {
            Map<String, Object> producerProperties = buildProducerProperties(
                    springWolfKafkaConfigProperties.getPublishing().getProducer());
            DefaultKafkaProducerFactory<Object, Object> producerFactory =
                    new DefaultKafkaProducerFactory<>(producerProperties);
            kafkaTemplate = Optional.of(new KafkaTemplate<>(producerFactory));
        } else {
            kafkaTemplate = Optional.empty();
        }
    }

    private static Map<String, Object> buildProducerProperties(KafkaProperties.Producer producerConfiguration) {
        KafkaProperties kafkaProperties = new KafkaProperties();
        KafkaProperties.Producer producer = kafkaProperties.getProducer();
        BeanUtils.copyProperties(producerConfiguration, producer);
        BeanUtils.copyProperties(producerConfiguration.getSecurity(), producer.getSecurity());
        BeanUtils.copyProperties(producerConfiguration.getSsl(), producer.getSsl());
        producer.getProperties().putAll(producerConfiguration.getProperties());
        return KafkaConfigBuilder.of(kafkaProperties).producer().build();
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
