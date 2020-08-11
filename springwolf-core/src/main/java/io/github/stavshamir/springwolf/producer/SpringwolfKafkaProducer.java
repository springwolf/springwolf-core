package io.github.stavshamir.springwolf.producer;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.protocol.AsyncApiProtocolConfiguration;
import io.github.stavshamir.springwolf.configuration.protocol.KafkaProtocolConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class SpringwolfKafkaProducer {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    public SpringwolfKafkaProducer(@Autowired AsyncApiDocket docket) {
        AsyncApiProtocolConfiguration protocolConfiguration = docket.getProtocolConfiguration();

        if (!(protocolConfiguration instanceof KafkaProtocolConfiguration)) {
            log.warn("The injected protocol configuration is not a KafkafProtocolConfiguration - the prodcuer will not work");
            this.kafkaTemplate = null;
            return;
        }

        Map<String, Object> config = ((KafkaProtocolConfiguration) protocolConfiguration).getProducerConfiguration();
        DefaultKafkaProducerFactory<String, Map<String, Object>> factory = new DefaultKafkaProducerFactory<>(config);
        this.kafkaTemplate = new KafkaTemplate<>(factory);
    }

    public void send(String topic, Map<String, Object> payload) {
        kafkaTemplate.send(topic, payload);
    }

}
