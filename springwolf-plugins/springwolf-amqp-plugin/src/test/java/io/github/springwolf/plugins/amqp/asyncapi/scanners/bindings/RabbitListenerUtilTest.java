// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeType;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelQueueProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelType;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class RabbitListenerUtilTest {

    private final RabbitListenerUtilContext emptyContext =
            new RabbitListenerUtilContext(emptyMap(), emptyMap(), emptyMap());

    private final org.springframework.amqp.core.Queue queue =
            new org.springframework.amqp.core.Queue("queue-1", false, true, true);
    private final RabbitListenerUtilContext queueContext =
            RabbitListenerUtilContext.create(List.of(queue), List.of(), List.of());

    private final TopicExchange exchange = new TopicExchange("exchange-name", false, true);
    private final RabbitListenerUtilContext exchangeContext =
            RabbitListenerUtilContext.create(List.of(queue), List.of(exchange), List.of());

    StringValueResolver stringValueResolver = mock(StringValueResolver.class);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> {
                    String arg = (String) invocation.getArguments()[0];
                    return switch (arg) {
                        case "${queue-1}" -> "queue-1";
                        case "${queue-1}-id" -> "queue-1-id";
                        case "${routing-key}" -> "routing-key";
                        case "${exchange-name}" -> "exchange-name";
                        default -> arg;
                    };
                })
                .when(stringValueResolver)
                .resolveStringValue(any());
    }

    @Nested
    class QueuesConfiguration {

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding = RabbitListenerUtil.buildChannelBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.QUEUE)
                            .queue(AMQPChannelQueueProperties.builder()
                                    .name("queue-1")
                                    .durable(false)
                                    .autoDelete(true)
                                    .exclusive(true)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        /**
         * Technically an invalid configuration as queue will be part of the spring context
         */
        @Test
        void buildChannelBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.QUEUE)
                            .queue(AMQPChannelQueueProperties.builder()
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

            // when
            Map<String, OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        /**
         * Technically an invalid configuration as queue will be part of the spring context
         */
        @Test
        void buildOperationBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesConfiguration.class);

            // when
            Map<String, OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

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
    class QueuesToDeclareConfiguration {

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesToDeclare.class);

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("queue-1", channelName);
        }

        /**
         * Technically an invalid configuration as context should be empty
         */
        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesToDeclare.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding = RabbitListenerUtil.buildChannelBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.QUEUE)
                            .queue(AMQPChannelQueueProperties.builder()
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
        void buildChannelBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesToDeclare.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.QUEUE)
                            .queue(AMQPChannelQueueProperties.builder()
                                    .name("queue-1")
                                    .durable(true)
                                    .autoDelete(false)
                                    .exclusive(false)
                                    .vhost("/")
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        /**
         * Technically an invalid configuration as context should be empty
         */
        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesToDeclare.class);

            // when
            Map<String, OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        @Test
        void buildOperationBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithQueuesToDeclare.class);

            // when
            Map<String, OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = RabbitListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), messageBinding.keySet());
            assertEquals(new AMQPMessageBinding(), messageBinding.get("amqp"));
        }

        private static class ClassWithQueuesToDeclare {
            // Note: Bean should not be in the context as it is created (declared) by the annotation
            @RabbitListener(queuesToDeclare = @Queue(name = "${queue-1}"))
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class QueueBindingsConfiguration {

        @Test
        void getChannelId() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            String channelName = RabbitListenerUtil.getChannelId(annotation, stringValueResolver);

            // then
            assertEquals("exchange-name", channelName);
        }

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("exchange-name", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding = RabbitListenerUtil.buildChannelBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.ROUTING_KEY)
                            .exchange(AMQPChannelExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(AMQPChannelExchangeType.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildChannelBindingWithExchangeContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, exchangeContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.ROUTING_KEY)
                            .exchange(AMQPChannelExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(AMQPChannelExchangeType.TOPIC)
                                    .durable(false)
                                    .autoDelete(true)
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        /**
         * Technically an invalid configuration as queue and exchange will be part of the spring context
         */
        @Test
        void buildChannelBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.ROUTING_KEY)
                            .exchange(AMQPChannelExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(AMQPChannelExchangeType.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, queueContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        @Test
        void buildOperationBindingWithExchangeContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding = RabbitListenerUtil.buildOperationBinding(
                    annotation, RabbitListenerUtilTest.this.stringValueResolver, exchangeContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        /**
         * Technically an invalid configuration as queue and exchange will be part of the spring context
         */
        @Test
        void buildOperationBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingConfiguration.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
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

        private static class ClassWithBindingConfiguration {
            @RabbitListener(
                    bindings = {
                        @QueueBinding(
                                exchange = @Exchange(name = "${exchange-name}"),
                                value = @Queue(name = "${queue-1}"))
                    })
            private void methodWithAnnotation(String payload) {}
        }
    }

    @Nested
    class QueueBindingWithRoutingKeyConfiguration {
        @Test
        void getChannelId() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            String channelName = RabbitListenerUtil.getChannelId(annotation, stringValueResolver);

            // then
            assertEquals("exchange-name", channelName);
        }

        @Test
        void getChannelName() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            String channelName = RabbitListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("exchange-name", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, queueContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.ROUTING_KEY)
                            .exchange(AMQPChannelExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(AMQPChannelExchangeType.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildChannelBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            Map<String, ? extends ChannelBinding> channelBinding =
                    RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), channelBinding.keySet());
            assertEquals(
                    AMQPChannelBinding.builder()
                            .is(AMQPChannelType.ROUTING_KEY)
                            .exchange(AMQPChannelExchangeProperties.builder()
                                    .name("exchange-name")
                                    .type(AMQPChannelExchangeType.DIRECT)
                                    .durable(true)
                                    .autoDelete(false)
                                    .build())
                            .build(),
                    channelBinding.get("amqp"));
        }

        @Test
        void buildOperationBinding() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, queueContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
        }

        @Test
        void buildOperationBindingWithEmptyContext() {
            // given
            RabbitListener annotation = getAnnotation(ClassWithBindingsAndRoutingKeyConfiguration.class);

            // when
            Map<String, ? extends OperationBinding> operationBinding =
                    RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, emptyContext);

            // then
            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("amqp"), operationBinding.keySet());
            assertEquals(AMQPOperationBinding.builder().build(), operationBinding.get("amqp"));
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
                                exchange = @Exchange(name = "${exchange-name}"),
                                key = "${routing-key}",
                                value = @Queue(name = "${queue-1}"))
                    })
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static RabbitListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(RabbitListener.class);
    }
}
