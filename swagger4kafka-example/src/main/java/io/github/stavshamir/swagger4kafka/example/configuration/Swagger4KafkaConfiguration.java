package io.github.stavshamir.swagger4kafka.example.configuration;

import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.asyncapi.types.server.Server;
import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import io.github.stavshamir.swagger4kafka.configuration.EnableSwagger4Kafka;
import io.github.stavshamir.swagger4kafka.configuration.protocol.KafkaProtocolConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger4Kafka
public class Swagger4KafkaConfiguration {

    private final String BOOTSTRAP_SERVERS;

    public Swagger4KafkaConfiguration(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {
        this.BOOTSTRAP_SERVERS = bootstrapServers;
    }

    @Bean
    public AsyncApiDocket asyncApiDocket() {
        Info info = Info.builder()
                .version("1.0.0")
                .title("swagger4kafka example project")
                .build();

        KafkaProtocolConfiguration kafka = KafkaProtocolConfiguration.builder()
                .basePackage("io.github.stavshamir.swagger4kafka.example.consumers")
                .build();

        return AsyncApiDocket.builder()
                .info(info)
                .server("kafka", Server.kafka().url(BOOTSTRAP_SERVERS).build())
                .protocol(kafka)
                .build();
    }

}
