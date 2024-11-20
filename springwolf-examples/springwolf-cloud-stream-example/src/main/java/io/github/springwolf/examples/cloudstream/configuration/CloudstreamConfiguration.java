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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Configuration
public class CloudstreamConfiguration {
    @Bean
    public Function<ExamplePayloadDto, AnotherPayloadDto> process() {
        return input -> {
            log.info("Received new message in example-topic: {}", input);
            AnotherPayloadDto output = new AnotherPayloadDto("foo", input);
            log.info("Publishing output: {}", output);
            return output;
        };
    }

    @Bean
    public BiFunction<ExamplePayloadDto, Map<String, Object>, AnotherPayloadDto> biProcess() {
        return (input, headers) -> {
            log.info("Received new message in bifunction-topic: {}. Headers: {}", input, headers);
            AnotherPayloadDto output = new AnotherPayloadDto("foo", input);
            log.info("Publishing output: {}", output);
            return output;
        };
    }

    @Bean
    public Consumer<AnotherPayloadDto> consumerMethod() {
        return input -> log.info("Received new message in another-topic: {}", input.toString());
    }

    @Bean
    public BiConsumer<AnotherPayloadDto, Map<String, Object>> biConsumerMethod() {
        return (input, headers) ->
                log.info("Received new message in biconsumer-topic: {}. Headers {}.", input.toString(), headers);
    }

    @GooglePubSubAsyncChannelBinding(
            schemaSettings = @GooglePubSubAsyncSchemaSetting(encoding = "BINARY", name = "project/test"))
    @GooglePubSubAsyncMessageBinding(schema = @GooglePubSubAsyncMessageSchema(name = "project/test"))
    @Bean
    public Consumer<GooglePubSubPayloadDto> googlePubSubConsumerMethod() {
        return input -> log.info("Received new message in google-pubsub-topic: {}", input.toString());
    }
}
