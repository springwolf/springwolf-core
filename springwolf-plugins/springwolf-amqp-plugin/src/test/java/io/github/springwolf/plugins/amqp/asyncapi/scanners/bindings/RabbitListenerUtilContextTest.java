// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class RabbitListenerUtilContextTest {
    @Test
    void testEmptyContext() {
        // when
        RabbitListenerUtilContext context = RabbitListenerUtilContext.create(List.of(), List.of(), List.of());

        // then
        assertEquals(Map.of(), context.queueMap());
        assertEquals(Map.of(), context.exchangeMap());
        assertEquals(Map.of(), context.bindingMap());
    }

    @Test
    void testWithSingleTopic() {
        org.springframework.amqp.core.Queue queue =
                new org.springframework.amqp.core.Queue("queue-1", false, true, true);
        TopicExchange exchange = new TopicExchange("exchange-name", false, true);
        Binding binding = BindingBuilder.bind(queue).to(exchange).with("routing-key");

        // when
        RabbitListenerUtilContext context =
                RabbitListenerUtilContext.create(List.of(queue), List.of(exchange), List.of(binding));

        // then
        assertEquals(Map.of("queue-1", queue), context.queueMap());
        assertEquals(Map.of("exchange-name", exchange), context.exchangeMap());
        assertEquals(Map.of("queue-1", binding), context.bindingMap());
    }

    @Test
    void testWithMultipleBeansForOneTopic() {
        org.springframework.amqp.core.Queue queueBean =
                new org.springframework.amqp.core.Queue("queue-1", false, true, true);
        TopicExchange exchangeBean = new TopicExchange("exchange-name", false, true);
        Binding bindingBean = BindingBuilder.bind(queueBean).to(exchangeBean).with("routing-key");

        // In this test, annotation values are different compared to the beans.
        // This might happen due to ill user configuration, but like Spring AMQP Springwolf tries to handle it
        org.springframework.amqp.core.Queue queueAnnotation =
                new org.springframework.amqp.core.Queue("queue-1", false, false, false);
        TopicExchange exchangeAnnotation = new TopicExchange("exchange-name", true, false);
        Binding bindingAnnotation =
                BindingBuilder.bind(queueAnnotation).to(exchangeAnnotation).with("routing-key");

        // when
        RabbitListenerUtilContext context = RabbitListenerUtilContext.create(
                List.of(queueBean, queueAnnotation),
                List.of(exchangeBean, exchangeAnnotation),
                List.of(bindingBean, bindingAnnotation));

        // then
        assertThat(context.queueMap()).hasSize(1);
        assertThat(context.queueMap().get("queue-1")).isIn(queueBean, queueAnnotation);
        assertThat(context.exchangeMap()).hasSize(1);
        assertThat(context.exchangeMap().get("exchange-name")).isIn(exchangeBean, exchangeAnnotation);
        assertThat(context.bindingMap()).hasSize(1);
        assertThat(context.bindingMap().get("queue-1")).isIn(bindingBean, bindingAnnotation);
    }
}
