package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestBeanConfiguration {

    @Bean
    public TestBean testBean() {
        return new TestBean("foo");
    }
}
