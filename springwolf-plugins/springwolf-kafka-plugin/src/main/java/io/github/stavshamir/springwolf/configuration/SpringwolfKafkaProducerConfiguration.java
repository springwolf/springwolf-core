package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.github.stavshamir.springwolf.SpringWolfKafkaConfigConstants.SPRINGWOLF_KAFKA_CONFIG_PREFIX;
import static io.github.stavshamir.springwolf.SpringWolfKafkaConfigConstants.SPRING_WOLF_KAFKA_PUBLISHING_ENABLED;

@Configuration
@ConditionalOnProperty(prefix = SPRINGWOLF_KAFKA_CONFIG_PREFIX, name = SPRING_WOLF_KAFKA_PUBLISHING_ENABLED)
public class SpringwolfKafkaProducerConfiguration {

    @Bean
    public SpringwolfKafkaProducer springwolfKafkaProducer(@Autowired SpringwolfKafkaTemplateFactory templateFactory) {
        return new SpringwolfKafkaProducer(templateFactory.buildKafkaTemplate());
    }

}
