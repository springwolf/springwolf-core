package io.github.stavshamir.springwolf.example.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class SpringwolfStompExampleApplication {

    // TODO: temporary fix, remove later
    @Bean
    @Primary
    public TaskExecutor primaryTaskExecutor() {
        return new TaskExecutorBuilder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringwolfStompExampleApplication.class, args);
    }
}
