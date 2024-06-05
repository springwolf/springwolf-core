// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.configuration;

import io.github.springwolf.examples.amqp.AmqpConstants;
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
        return new Queue(AmqpConstants.EXAMPLE_QUEUE, false);
    }

    @Bean
    public Queue anotherQueue() {
        return new Queue(AmqpConstants.ANOTHER_QUEUE, false);
    }

    @Bean
    public Queue exampleBindingsQueue() {
        return new Queue(AmqpConstants.EXAMPLE_BINDINGS_QUEUE, false, true, true);
    }

    @Bean
    public Exchange exampleTopicExchange() {
        return new TopicExchange(AmqpConstants.EXAMPLE_TOPIC_EXCHANGE);
    }

    @Bean
    public Queue multiPayloadQueue() {
        return new Queue(AmqpConstants.MULTI_PAYLOAD_QUEUE);
    }

    @Bean
    public Binding exampleTopicBinding(Queue exampleBindingsQueue, Exchange exampleTopicExchange) {
        return BindingBuilder.bind(exampleBindingsQueue)
                .to(exampleTopicExchange)
                .with(AmqpConstants.EXAMPLE_TOPIC_ROUTING_KEY)
                .noargs();
    }
}
