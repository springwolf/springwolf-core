package io.github.stavshamir.springwolf.example.amqp.producers;

import io.github.stavshamir.springwolf.example.amqp.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static io.github.stavshamir.springwolf.example.amqp.configuration.KafkaConfiguration.PRODUCER_TOPIC;

@Component
public class AnotherProducer {

    @Autowired
    private KafkaTemplate<String, AnotherPayloadDto> kafkaTemplate;

    public void sendMessage(AnotherPayloadDto msg) {
        kafkaTemplate.send(PRODUCER_TOPIC, msg);
    }

}
