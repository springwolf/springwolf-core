package io.github.stavshamir.swagger4kafka.example.consumers;

import io.github.stavshamir.swagger4kafka.example.dtos.ExamplePayloadDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExampleConsumer {

    @KafkaListener(topics = "example-topic", containerFactory = "exampleKafkaListenerContainerFactory")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        System.out.println(payload);
    }

}
