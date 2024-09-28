// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SqsListenerUtilTest {
    private static final String QUEUE = "test-queue";

    @Nested
    class FullConfiguration {
        @Test
        void getChannelName() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);
            when(stringValueResolver.resolveStringValue("${queue-1}")).thenReturn("queue-1");

            // when
            String channelName = SqsListenerUtil.getChannelName(annotation, stringValueResolver);

            // then
            assertEquals("queue-1", channelName);
        }

        @Test
        void buildChannelBinding() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);

            // when
            Map<String, ChannelBinding> channelBinding =
                    SqsListenerUtil.buildChannelBinding(annotation, stringValueResolver);

            // then
            var expectedChannel = SQSChannelBinding.builder()
                    .queue(SQSChannelBindingQueue.builder()
                            .name("${queue-1}")
                            .fifoQueue(true)
                            .build())
                    .build();

            assertEquals(1, channelBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), channelBinding.keySet());
            assertEquals(expectedChannel, channelBinding.get("sqs"));
        }

        @Test
        void buildOperationBinding() {
            // given
            SqsListener annotation = getAnnotation(ClassWithFullSqsListenerConfiguration.class);
            StringValueResolver stringValueResolver = mock(StringValueResolver.class);

            // when
            Map<String, OperationBinding> operationBinding =
                    SqsListenerUtil.buildOperationBinding(annotation, stringValueResolver);

            // then
            var expectedOperation = SQSOperationBinding.builder()
                    .queues(List.of(SQSChannelBindingQueue.builder()
                            .name("${queue-1}")
                            .fifoQueue(true)
                            .build()))
                    .build();

            assertEquals(1, operationBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), operationBinding.keySet());
            assertEquals(expectedOperation, operationBinding.get("sqs"));
        }

        @Test
        void buildMessageBinding() {
            // when
            Map<String, MessageBinding> messageBinding = SqsListenerUtil.buildMessageBinding();

            // then
            assertEquals(1, messageBinding.size());
            assertEquals(Sets.newTreeSet("sqs"), messageBinding.keySet());
            assertEquals(new SQSMessageBinding(), messageBinding.get("sqs"));
        }

        private static class ClassWithFullSqsListenerConfiguration {

            @SqsListener("${queue-1}")
            private void methodWithAnnotation(String payload) {}
        }
    }

    private static SqsListener getAnnotation(Class<?> clazz) {
        return clazz.getDeclaredMethods()[0].getAnnotation(SqsListener.class);
    }
}
