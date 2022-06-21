package io.github.stavshamir.springwolf.example.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.AmqpConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.AmqpProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAsyncApi
public class AsyncApiConfiguration {

    private final String amqpHost;
    private final String amqpPort;

    public AsyncApiConfiguration(
            @Value("${spring.rabbitmq.host}") String amqpHost,
            @Value("${spring.rabbitmq.port}") int amqpPort) {
        this.amqpHost = amqpHost;
        this.amqpPort = String.valueOf(amqpPort);
    }

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project - AMQP")
                .build();

        Server amqp = Server.builder()
                .protocol("amqp")
                .url(String.format("%s:%s", amqpHost, amqpPort))
                .build();

        AmqpProducerData exampleProducer = AmqpProducerData.amqpProducerDataBuilder()
                .queueName("example-producer-channel")
                .exchangeName("example-topic-exchange")
                .routingKey("example-topic-routing-key")
                .payloadType(AnotherPayloadDto.class)
                .build();

        AmqpConsumerData exampleConsumer = AmqpConsumerData.amqpConsumerDataBuilder()
                .queueName("example-manual-consumer-channel")
                .exchangeName("example-consumer-topic-exchange")
                .routingKey("example-consumer-topic-routing-key")
                .payloadType(AnotherPayloadDto.class)
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("amqp", amqp)
                .producer(exampleProducer)
                .consumer(exampleConsumer)
                .build();
    }

}
