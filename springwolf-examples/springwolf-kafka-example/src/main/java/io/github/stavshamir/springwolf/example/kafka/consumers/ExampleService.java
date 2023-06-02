package io.github.stavshamir.springwolf.example.kafka.consumers;

import io.github.stavshamir.springwolf.example.kafka.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExampleService {

    public void doSomething(ExamplePayloadDto payload) {
        log.info("Received new message in example-topic: {}", payload.toString());
    }
}
