// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.stomp.stomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class SpringwolfStompExampleApplication {

    // TODO: temporary fix, remove later
    @Bean
    @Primary
    public TaskExecutor primaryTaskExecutor() {
        return new ThreadPoolTaskExecutorBuilder().build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringwolfStompExampleApplication.class, args);
    }
}
