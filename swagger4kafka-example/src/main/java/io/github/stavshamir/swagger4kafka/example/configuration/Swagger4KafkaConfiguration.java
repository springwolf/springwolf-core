package io.github.stavshamir.swagger4kafka.example.configuration;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.swagger4kafka.asyncapi.types.info.Info;
import io.github.stavshamir.swagger4kafka.asyncapi.types.server.Server;
import io.github.stavshamir.swagger4kafka.configuration.AsyncApiDocket;
import io.github.stavshamir.swagger4kafka.configuration.EnableSwagger4Kafka;
import io.github.stavshamir.swagger4kafka.configuration.protocol.KafkaProtocolConfiguration;
import io.github.stavshamir.swagger4kafka.configuration.protocol.Protocols;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

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
                .producerConfiguration(buildProducerConfiguration(BOOTSTRAP_SERVERS))
                .build();

        return AsyncApiDocket.builder()
                .info(info)
                .server("kafka", Server.kafka().url(BOOTSTRAP_SERVERS).build())
                .protocols(Protocols.builder().kafka(kafka).build())
                .build();
    }

    private Map<String, Object> buildProducerConfiguration(String bootstrapServers) {
        return ImmutableMap.<String, Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
                .put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false)
                .build();
    }

}
