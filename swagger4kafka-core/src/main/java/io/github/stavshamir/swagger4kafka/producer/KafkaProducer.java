package io.github.stavshamir.swagger4kafka.producer;

import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    public KafkaProducer(@Autowired AsyncApiDocket docket) {
        Map<String, Object> config = docket.getProtocols().getKafka().getProducerConfiguration();
        DefaultKafkaProducerFactory<String, Map<String, Object>> factory = new DefaultKafkaProducerFactory<>(config);
        this.kafkaTemplate = new KafkaTemplate<>(factory);
    }

    public void send(String topic, Map<String, Object> payload) {
        kafkaTemplate.send(topic, payload);
    }

}
