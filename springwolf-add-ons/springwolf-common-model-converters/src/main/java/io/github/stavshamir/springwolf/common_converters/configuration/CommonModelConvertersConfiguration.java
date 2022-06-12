package io.github.stavshamir.springwolf.common_converters.configuration;

import io.github.stavshamir.springwolf.common_converters.converters.MonetaryAmountConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonModelConvertersConfiguration {

    @Bean
    public MonetaryAmountConverter monetaryAmountConverter(){
        return new MonetaryAmountConverter();
    }
}
