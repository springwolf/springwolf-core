package io.github.stavshamir.springwolf.example.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.asyncapi.types.KafkaProducerData;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
import io.github.stavshamir.springwolf.example.dtos.AnotherPayloadDto;
import io.github.stavshamir.springwolf.example.dtos.ExamplePayloadDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.stavshamir.springwolf.example.configuration.KafkaConfiguration.PRODUCER_TOPIC;

@Configuration
@EnableAsyncApi
public class AsyncApiConfiguration {

    private final String BOOTSTRAP_SERVERS;

    public AsyncApiConfiguration(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {
        this.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("Springwolf example project")
                .build();

        KafkaProducerData exampleProducerData = KafkaProducerData.kafkaProducerDataBuilder()
                .topicName(PRODUCER_TOPIC)
                .payloadType(ExamplePayloadDto.class)
                .build();

        KafkaProducerData anotherProducerData = KafkaProducerData.kafkaProducerDataBuilder()
                .topicName(PRODUCER_TOPIC)
                .payloadType(AnotherPayloadDto.class)
                .build();

        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("kafka", Server.builder().protocol("kafka").url(BOOTSTRAP_SERVERS).build())
                .producer(exampleProducerData)
                .producer(anotherProducerData)
                .build();
    }

}
