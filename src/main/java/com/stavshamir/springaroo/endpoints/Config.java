package com.stavshamir.springaroo.endpoints;

import com.stavshamir.springaroo.model.Models;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public KafkaListenersScanner kafkaListenersScanner() {
        return new KafkaListenersScanner(new Models());
    }

}
