// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    // Required so that the Rabbit Listeners will be able to receive json serialized messages
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue exampleQueue() {
        return new Queue("example-queue", false);
    }

    @Bean
    public Queue anotherQueue() {
        return new Queue("another-queue", false);
    }

    @Bean
    public Queue exampleBindingsQueue() {
        return new Queue("example-bindings-queue", false, true, true);
    }

    @Bean
    public Exchange exampleTopicExchange() {
        return new TopicExchange("example-topic-exchange");
    }

    @Bean
    public Queue exampleTopicQueue() {
        return new Queue("example-topic-queue");
    }

    @Bean
    public Binding exampleTopicBinding(Queue exampleTopicQueue, Exchange exampleTopicExchange) {
        return BindingBuilder.bind(exampleTopicQueue)
                .to(exampleTopicExchange)
                .with("example-topic-routing-key")
                .noargs();
    }
}
