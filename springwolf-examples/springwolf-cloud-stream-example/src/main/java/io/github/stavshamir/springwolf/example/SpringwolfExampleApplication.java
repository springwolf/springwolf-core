package io.github.stavshamir.springwolf.example;

import io.github.stavshamir.springwolf.example.payload.ConsumerPayload;
import io.github.stavshamir.springwolf.example.payload.InputPayload;
import io.github.stavshamir.springwolf.example.payload.OutputPayload;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;


@SpringBootApplication
public class SpringwolfExampleApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringwolfExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringwolfExampleApplication.class, args);
    }

    @Bean
    public Function<KStream<Void, InputPayload>, KStream<Void, OutputPayload>> process() {
        return input -> input
                .peek((k, v) -> logger.info("Received payload: {}", v))
                .mapValues(v -> new OutputPayload(v.getFoo().stream().findFirst().orElse("list was empty"), v.getBar()))
                .peek((k, v) -> logger.info("Publishing output: {}", v));
    }

    @Bean
    public Consumer<ConsumerPayload> consumerMethod() {
        return input -> logger.info("Consumed: {}", input);
    }

}
