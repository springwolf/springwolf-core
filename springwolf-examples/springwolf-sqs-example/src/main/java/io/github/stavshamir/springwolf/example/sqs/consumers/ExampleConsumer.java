package io.github.stavshamir.springwolf.example.sqs.consumers;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.example.sqs.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.sqs.dtos.ExamplePayloadDto;
import io.github.stavshamir.springwolf.example.sqs.producers.AnotherProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExampleConsumer {
    private final AnotherProducer anotherProducer;

    @SqsListener("example-queue")
    public void receiveExamplePayload(ExamplePayloadDto payload) {
        log.info("Received new message in example-queue: {}", payload.toString());

        AnotherPayloadDto example = new AnotherPayloadDto();
        example.setExample(payload);
        example.setFoo("foo");

        anotherProducer.sendMessage(example);
    }

    @SqsListener("another-queue")
    public void receiveAnotherPayload(AnotherPayloadDto payload) {
        log.info("Received new message in another-queue: {}", payload.toString());
    }
}
