package io.github.stavshamir.springwolf.example.kafka.producers;

import io.github.stavshamir.springwolf.example.kafka.configuration.KafkaConfiguration;
import io.github.stavshamir.springwolf.example.kafka.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AnotherProducer {

    @Autowired
    private KafkaTemplate<String, AnotherPayloadDto> kafkaTemplate;

    public void sendMessage(AnotherPayloadDto msg) {
        kafkaTemplate.send(KafkaConfiguration.PRODUCER_TOPIC, msg);
    }
}
