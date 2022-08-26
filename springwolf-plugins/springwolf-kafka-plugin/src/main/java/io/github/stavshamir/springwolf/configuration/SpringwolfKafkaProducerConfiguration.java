package io.github.stavshamir.springwolf.configuration;

import io.github.stavshamir.springwolf.producer.SpringwolfKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringwolfKafkaProducerConfiguration {

    @Bean
    public SpringwolfKafkaProducer springwolfKafkaProducer(@Autowired SpringwolfKafkaTemplateFactory templateFactory) {
        return new SpringwolfKafkaProducer(templateFactory.buildKafkaTemplate());
    }

}
