// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RabbitListenerUtilTest {

    private final RabbitListenerUtil.RabbitListenerUtilContext emptyContext =
            new RabbitListenerUtil.RabbitListenerUtilContext(emptyMap(), emptyMap(), emptyMap());

    @Nested
    public class QueuesConfiguration {

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is("queue")
                            .exchange(AMQPChannelBinding.ExchangeProperties.builder()
                                    .name("")
                                    .type(ExchangeTypes.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .vhost("/")
                                    .build())
                            .queue(AMQPChannelBinding.QueueProperties.builder()
                                    .name("queue-1")
                                    .durable(true)
                                    .autoDelete(false)
                                    .exclusive(false)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().cc(List.of("queue-1")).build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithQueuesConfiguration {

            @RabbitListener(queues = "${queue-1}")
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class QueueBindingsConfiguration {
        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is("routingKey")
                            .exchange(AMQPChannelBinding.ExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(ExchangeTypes.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .queue(AMQPChannelBinding.QueueProperties.builder()
                                    .name("queue-1")
                                    .durable(true)
                                    .autoDelete(false)
                                    .exclusive(false)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().cc(List.of("")).build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithBindingsConfiguration {

            @RabbitListener(
                    bindings = {
                        @QueueBinding(exchange = @Exchange(name = "exchange-name"), value = @Queue(name = "${queue-1}"))
                    })
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class QueueBindingWithRoutingKeyConfiguration {
        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${routing-key}")).thenReturn("routing-key");

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("routing-key", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is("routingKey")
                            .exchange(AMQPChannelBinding.ExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(ExchangeTypes.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .queue(AMQPChannelBinding.QueueProperties.builder()
                                    .name("queue-1")
                                    .durable(true)
                                    .autoDelete(false)
                                    .exclusive(false)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${routing-key}")).thenReturn("routing-key");

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, resolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(
                    AMQPOperationBinding.builder().cc(List.of("routing-key")).build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithBindingsAndRoutingKeyConfiguration {

            @RabbitListener(
                    bindings = {
                        @QueueBinding(
                                exchange = @Exchange(name = "exchange-name"),
                                key = "${routing-key}",
                                value = @Queue(name = "${queue-1}"))
                    })
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class QueueBindingsWithBeansConfiguration {
        private final RabbitListenerUtil.RabbitListenerUtilContext context;

        // Simulate a RabbitListenerUtilContext that has already been populated by exising spring beans
        {
            org.springframework.amqp.core.Queue queue =
                    new org.springframework.amqp.core.Queue("queue-1", false, true, true);
            TopicExchange exchange = new TopicExchange("exchange-name", false, true);
            context = RabbitListenerUtil.RabbitListenerUtilContext.create(
                    List.of(queue),
                    List.of(exchange),
                    List.of(BindingBuilder.bind(queue).to(exchange).with("routing-key")));
        }

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");
            when(resolver.resolveStringValue("${routing-key}")).thenReturn("routing-key");

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, resolver);

            // then
            assertEquals("routing-key", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");
            when(resolver.resolveStringValue("${routing-key}")).thenReturn("routing-key");

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, resolver, context);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is("routingKey")
                            .exchange(AMQPChannelBinding.ExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(ExchangeTypes.TOPIC)
                                    .durable(false)
                                    .autoDelete(true)
                                    .build())
                            .queue(AMQPChannelBinding.QueueProperties.builder()
                                    .name("queue-1")
                                    .durable(false)
                                    .autoDelete(true)
                                    .exclusive(true)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsConfiguration.class);
            StringValueResolver resolver = mock(StringValueResolver.class);
            when(resolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");
            when(resolver.resolveStringValue("${routing-key}")).thenReturn("routing-key");

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, resolver, context);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(
                    AMQPOperationBinding.builder().cc(List.of("routing-key")).build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, ? extends MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithBindingsConfiguration {

            @RabbitListener(
                    bindings = {
                        @QueueBinding(
                                exchange = @Exchange(name = "exchange-name"),
                                value = @Queue(name = "${queue-1}"),
                                key = "${routing-key}"),
                    })
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    public class RabbitListenerUtilContextTest {
        @Test
        void testEmptyContext() {
            // when
            RabbitListenerUtil.RabbitListenerUtilContext context =
                    RabbitListenerUtil.RabbitListenerUtilContext.create(List.of(), List.of(), List.of());

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
            RabbitListenerUtil.RabbitListenerUtilContext context = RabbitListenerUtil.RabbitListenerUtilContext.create(
                    List.of(queue), List.of(exchange), List.of(binding));

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
            Binding bindingBean =
                    BindingBuilder.bind(queueBean).to(exchangeBean).with("routing-key");

            // In this test, annotation values are different compared to the beans.
            // This might happen due to ill user configuration, but like Spring AMQP Springwolf tries to handle it
            org.springframework.amqp.core.Queue queueAnnotation =
                    new org.springframework.amqp.core.Queue("queue-1", false, false, false);
            TopicExchange exchangeAnnotation = new TopicExchange("exchange-name", true, false);
            Binding bindingAnnotation =
                    BindingBuilder.bind(queueAnnotation).to(exchangeAnnotation).with("routing-key");

            // when
            RabbitListenerUtil.RabbitListenerUtilContext context = RabbitListenerUtil.RabbitListenerUtilContext.create(
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

    private static RabbitListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(RabbitListener.class);
    }
}
