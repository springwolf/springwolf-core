package io.github.stavshamir.springwolf.example.cloudstream;

import io.github.stavshamir.springwolf.example.cloudstream.payload.ConsumerPayload;
import io.github.stavshamir.springwolf.example.cloudstream.payload.InputPayload;
import io.github.stavshamir.springwolf.example.cloudstream.payload.OutputPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class SpringwolfCloudstreamExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringwolfCloudstreamExampleApplication.class, args);
    }

    @Bean
    public Function<KStream<Void, InputPayload>, KStream<Void, OutputPayload>> process() {
        return input -> input.peek((k, v) -> log.info("Received payload: {}", v))
                .mapValues(
                        v -> new OutputPayload(v.getFoo().stream().findFirst().orElse("list was empty"), v.getBar()))
                .peek((k, v) -> log.info("Publishing output: {}", v));
    }

    @Bean
    public Consumer<ConsumerPayload> consumerMethod() {
        return input -> log.info("Consumed: {}", input);
    }
}
