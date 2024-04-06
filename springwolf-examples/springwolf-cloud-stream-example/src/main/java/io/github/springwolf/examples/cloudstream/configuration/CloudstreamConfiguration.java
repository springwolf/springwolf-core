// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.cloudstream.configuration;

import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageBinding;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageSchema;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncSchemaSetting;
import io.github.springwolf.examples.cloudstream.dtos.AnotherPayloadDto;
import io.github.springwolf.examples.cloudstream.dtos.ExamplePayloadDto;
import io.github.springwolf.examples.cloudstream.dtos.GooglePubSubPayloadDto;
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

    @GooglePubSubAsyncChannelBinding(
            schemaSettings = @GooglePubSubAsyncSchemaSetting(encoding = "BINARY", name = "project/test"))
    @GooglePubSubAsyncMessageBinding(schema = @GooglePubSubAsyncMessageSchema(name = "project/test"))
    @Bean
    public Consumer<GooglePubSubPayloadDto> googlePubSubConsumerMethod() {
        return input -> log.info("Received new message in google-pubsub-topic: {}", input.toString());
    }
}
