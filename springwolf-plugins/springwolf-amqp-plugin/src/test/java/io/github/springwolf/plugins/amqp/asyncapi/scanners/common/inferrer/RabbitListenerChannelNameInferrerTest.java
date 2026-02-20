// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.common.inferrer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class RabbitListenerChannelNameInferrerTest {

    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final RabbitListenerChannelNameInferrer inferrer =
            new RabbitListenerChannelNameInferrer(stringValueResolver);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> {
                    String arg = (String) invocation.getArguments()[0];
                    return switch (arg) {
                        case "${queue-1}" -> "queue-1";
                        case "${routing-key}" -> "routing-key";
                        case "${exchange-name}" -> "exchange-name";
                        case "${exchange-name}_${routing-key}" -> "exchange-name_routing-key";
                        default -> arg;
                    };
                })
                .when(stringValueResolver)
                .resolveStringValue(any());
    }

    @Test
    void returnsEmptyWhenNoRabbitListenerAnnotation() throws Exception {
        // given
        Method method = NoAnnotationClass.class.getDeclaredMethod("method", String.class);

        // when
        Optional<String> result = inferrer.inferChannelName(method);

        // then
        assertThat(result).isEmpty();
    }

    @Nested
    class QueuesAttribute {
        @Test
        void infersChannelNameFromQueues() throws Exception {
            // given
            Method method = WithQueuesClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("queue-1");
        }
    }

    @Nested
    class QueuesToDeclareAttribute {
        @Test
        void infersChannelNameFromQueuesToDeclare() throws Exception {
            // given
            Method method = WithQueuesToDeclareClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("queue-1");
        }
    }

    @Nested
    class BindingsAttribute {
        @Test
        void infersChannelNameFromBindingsExchange() throws Exception {
            // given
            Method method = WithBindingsClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("exchange-name");
        }

        @Test
        void infersChannelNameFromBindingsExchangeWithRoutingKey() throws Exception {
            // given
            Method method = WithBindingsAndRoutingKeyClass.class.getDeclaredMethod("listen", String.class);

            // when
            Optional<String> result = inferrer.inferChannelName(method);

            // then
            assertThat(result).hasValue("exchange-name_routing-key");
        }
    }

    // ---- helper classes ----

    private static class NoAnnotationClass {
        private void method(String payload) {}
    }

    private static class WithQueuesClass {
        @RabbitListener(queues = "${queue-1}")
        private void listen(String payload) {}
    }

    private static class WithQueuesToDeclareClass {
        @RabbitListener(queuesToDeclare = @Queue(name = "${queue-1}"))
        private void listen(String payload) {}
    }

    private static class WithBindingsClass {
        @RabbitListener(
                bindings =
                        @QueueBinding(
                                value = @Queue(name = "${queue-1}"),
                                exchange = @Exchange(name = "${exchange-name}")))
        private void listen(String payload) {}
    }

    private static class WithBindingsAndRoutingKeyClass {
        @RabbitListener(
                bindings =
                        @QueueBinding(
                                value = @Queue(name = "${queue-1}"),
                                exchange = @Exchange(name = "${exchange-name}"),
                                key = "${routing-key}"))
        private void listen(String payload) {}
    }
}
