package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.SpringWolfKafkaConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@ConditionalOnBean(value = SpringwolfKafkaProducerConfiguration.class)
public class SpringwolfKafkaTemplateFactory {

    private final SpringWolfKafkaConfigProperties springWolfKafkaConfigProperties;

    public Optional<KafkaTemplate<Object, Object>> buildKafkaTemplate() {
        Optional<KafkaTemplate<Object, Object>> kafkaTemplate = Optional.empty();

        if (springWolfKafkaConfigProperties.getPublishing() != null
                && springWolfKafkaConfigProperties.getPublishing().getProducer() != null) {
            Map<String, Object> producerProperties = springWolfKafkaConfigProperties.getPublishing().getProducer()
                    .buildProperties();
            DefaultKafkaProducerFactory<Object, Object> producerFactory = new DefaultKafkaProducerFactory<>(producerProperties);
            kafkaTemplate = Optional.of(new KafkaTemplate<>(producerFactory));
        }

        return kafkaTemplate;
    }
}
