package io.github.stavshamir.springwolf.example.kafka.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Demonstrate how to use spring-security with springwolf.
 */
@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {
    private static final String DOCS_ENDPOINT = "/springwolf/docs";
    private static final String KAFKA_PUBLISH_ENDPOINT = "/springwolf/kafka/publish";
    private static final String UI_RESOURCES = "/springwolf/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(it -> it
                        // anyone can read the springwolf docs + ui
                        // also, anyone can publish messages (as enabled in application.properties)
                        .requestMatchers(DOCS_ENDPOINT, UI_RESOURCES, KAFKA_PUBLISH_ENDPOINT)
                        .permitAll()
                        .anyRequest()
                        .denyAll())
                .build();
    }
}
