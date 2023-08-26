package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.example.kafka.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.kafka.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {

    private final AnotherProducer anotherProducer;

    @KafkaListener(topics = "example-topic")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        AnotherPayloadDto anotherPayloadDto = new AnotherPayloadDto();

        log.info("Received new message in anotherPayloadDto-queue: {}", payload.toString());;
        anotherPayloadDto.setExample(payload);
        anotherPayloadDto.setFoo("foo");

        anotherProducer.sendMessage(anotherPayloadDto);
    }

    @KafkaListener(topics = "another-topic", groupId = "example-group-id", batch = "true")
    public void receiveAnotherPayloadBatched(List<AnotherPayloadDto> payloads) {
        log.info("Received new messages in another-topic: {}", payloads.toString());
    }
}
