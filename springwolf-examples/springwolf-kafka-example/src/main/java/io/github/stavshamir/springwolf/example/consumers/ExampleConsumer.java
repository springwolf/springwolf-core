package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExampleConsumer {

    private final ExampleService exampleService;

    public ExampleConsumer(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @KafkaListener(topics = "example-topic")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        exampleService.doSomething(payload);
    }

    @KafkaListener(
            topics = "another-topic",
            groupId = "example-group-id",
            batch = "true"
    )
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }

}
