package io.github.stavshamir.springwolf.example.configuration;

import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.EnableAsyncApi;
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


        return AsyncApiDocket.builder()
                .basePackage("io.github.stavshamir.springwolf.example.consumers")
                .info(info)
                .server("amqp", amqp)
                .build();
    }

}
