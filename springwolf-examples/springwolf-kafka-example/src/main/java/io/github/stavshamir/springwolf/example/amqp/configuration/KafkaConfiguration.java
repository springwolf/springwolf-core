package io.github.stavshamir.springwolf.example.amqp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfiguration {

    public final static String PRODUCER_TOPIC = "example-producer-topic";

    public final static String CONSUMER_TOPIC = "example-consumer-topic";
}
