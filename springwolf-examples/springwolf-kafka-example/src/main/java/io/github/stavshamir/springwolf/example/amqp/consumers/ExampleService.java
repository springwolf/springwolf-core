package io.github.stavshamir.springwolf.example.amqp.consumers;

import io.github.stavshamir.springwolf.example.amqp.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExampleService {

    public void doSomething(ExamplePayloadDto payload) {
        log.info("Received new message in example-topic: {}", payload.toString());
    }
}
