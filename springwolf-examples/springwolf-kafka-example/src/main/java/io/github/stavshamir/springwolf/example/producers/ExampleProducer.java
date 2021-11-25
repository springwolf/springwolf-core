package io.github.stavshamir.springwolf.example.producers;

import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExampleProducer {

    @Autowired
    private KafkaTemplate<String, ExamplePayloadDto> kafkaTemplate;

    public void sendMessage(ExamplePayloadDto msg) {
        kafkaTemplate.send("example-producer-topic", msg);
    }

}
