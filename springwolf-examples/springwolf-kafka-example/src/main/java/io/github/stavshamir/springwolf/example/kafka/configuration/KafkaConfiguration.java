package io.github.stavshamir.springwolf.example.kafka.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    public static final String PRODUCER_TOPIC = "example-producer-topic";

    public static final String CONSUMER_TOPIC = "example-consumer-topic";
}
