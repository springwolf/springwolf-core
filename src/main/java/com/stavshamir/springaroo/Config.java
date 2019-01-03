package com.stavshamir.springaroo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public KafkaListenersScanner kafkaListenersScanner() {
        return new KafkaListenersScanner();
    }

}
