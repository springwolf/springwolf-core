package com.stavshamir.springaroo;

import com.stavshamir.springaroo.endpoints.KafkaListenersScanner;
import com.stavshamir.springaroo.model.Models;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public KafkaListenersScanner kafkaListenersScanner() {
        return new KafkaListenersScanner(models());
    }

    @Bean
    public Models models() {
        return new Models();
    }

}
