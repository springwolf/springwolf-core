package io.github.stavshamir.springwolf.example.consumers;

import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ExampleConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ExampleConsumer.class);
    @Bean
    public Consumer<ExamplePayloadDto> exampleNewConsumerMethod() {
        return t -> logger.info("Received new message in someGroup: {}", t.toString());
    }
}
