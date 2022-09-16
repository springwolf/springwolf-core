package io.github.stavshamir.springwolf.example.configuration;

import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.info.License;
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
                .contact(Contact.builder().name("springwolf").url("https://github.com/springwolf/springwolf-core").email("example@example.com").build())
                .description("Springwolf example project to demonstrate springwolfs abilities")
                .license(License.builder().name("Apache License 2.0").build())
                .build();

        Server amqp = Server.builder()
                .protocol("amqp")
                .url(String.format("%s:%s", amqpHost, amqpPort))
                .build();

        AmqpProducerData exampleProducer = AmqpProducerData.amqpProducerDataBuilder()
                .queueName("example-producer-channel")
                .description("example-producer-channel-description")
                .exchangeName("example-topic-exchange")
                .routingKey("example-topic-routing-key")
                .payloadType(AnotherPayloadDto.class)
                .build();

        AmqpConsumerData exampleManuallyDefinedConsumer = AmqpConsumerData.amqpConsumerDataBuilder()
                .queueName("example-manual-consumer-channel")
                .description("example-manual-consumer-channel-description")
                .exchangeName("example-consumer-topic-exchange")
                .routingKey("example-consumer-topic-routing-key")
                .payloadType(AnotherPayloadDto.class)
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("amqp", amqp)
                .producer(exampleProducer)
                .consumer(exampleManuallyDefinedConsumer)
                .build();
    }

}
