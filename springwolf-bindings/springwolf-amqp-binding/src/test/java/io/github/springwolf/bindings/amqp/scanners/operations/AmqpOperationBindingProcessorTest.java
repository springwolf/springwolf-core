// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.amqp.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.bindings.amqp.annotations.AmqpAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class AmqpOperationBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final AmqpOperationBindingProcessor processor = new AmqpOperationBindingProcessor(stringValueResolver);

    @BeforeEach
    void setUp() {
        doAnswer(invocation -> invocation.getArgument(0))
                .when(stringValueResolver)
                .resolveStringValue(anyString());
    }

    @Nested
    class EmptyAnnotation {
        @Test
        void mapToOperationBindingTest() throws NoSuchMethodException {
            AmqpAsyncOperationBinding annotation = EmptyAnnotation.class
                    .getMethod("methodWithAnnotation")
                    .getAnnotation(AmqpAsyncOperationBinding.class);

            ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

            assertThat(binding.getType()).isEqualTo("amqp");
            assertThat(binding.getBinding())
                    .isEqualTo(AMQPOperationBinding.builder()
                            .cc(List.of())
                            .bcc(List.of())
                            .priority(0)
                            .deliveryMode(1)
                            .mandatory(false)
                            .timestamp(false)
                            .ack(false)
                            .build());
        }

        @AmqpAsyncOperationBinding
        public void methodWithAnnotation() {}
    }

    @Nested
    class AllFieldsSetInAnnotation {
        @Test
        void mapToOperationBindingTest() throws NoSuchMethodException {
            AmqpAsyncOperationBinding annotation = AllFieldsSetInAnnotation.class
                    .getMethod("methodWithAnnotation")
                    .getAnnotation(AmqpAsyncOperationBinding.class);

            ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

            assertThat(binding.getType()).isEqualTo("amqp");
            assertThat(binding.getBinding())
                    .isEqualTo(AMQPOperationBinding.builder()
                            .expiration(1)
                            .userId("userId")
                            .cc(List.of("cc1", "cc2"))
                            .priority(2)
                            .deliveryMode(3)
                            .mandatory(true)
                            .bcc(List.of("bcc1", "bcc2"))
                            .timestamp(true)
                            .ack(true)
                            .build());
        }

        @AmqpAsyncOperationBinding(
                expiration = 1,
                userId = "userId",
                cc = {"cc1", "cc2"},
                priority = 2,
                deliveryMode = 3,
                mandatory = true,
                bcc = {"bcc1", "bcc2"},
                timestamp = true,
                ack = true)
        public void methodWithAnnotation() {}
    }
}
