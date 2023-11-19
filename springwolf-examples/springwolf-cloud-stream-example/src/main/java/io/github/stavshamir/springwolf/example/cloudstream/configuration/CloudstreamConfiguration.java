// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.cloudstream.configuration;

import io.github.stavshamir.springwolf.example.cloudstream.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.cloudstream.dtos.ExamplePayloadDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
public class CloudstreamConfiguration {
    @Bean
    public Function<KStream<Void, ExamplePayloadDto>, KStream<Void, AnotherPayloadDto>> process() {
        return input -> input.peek((k, v) -> log.info("Received new message in example-topic: {}", input))
                .mapValues(v -> new AnotherPayloadDto("foo", v))
                .peek((k, v) -> log.info("Publishing output: {}", v));
    }

    @Bean
    public Consumer<AnotherPayloadDto> consumerMethod() {
        return input -> log.info("Received new message in another-topic: {}", input.toString());
    }
}
