// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.amqp.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AmqpOperationBindingProcessorTest {
    private final AmqpOperationBindingProcessor processor = new AmqpOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        AmqpAsyncOperationBinding annotation = AmqpOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(AmqpAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        assertThat(binding.getType()).isEqualTo("amqp");
        assertThat(binding.getBinding())
                .isEqualTo(AMQPOperationBinding.builder()
                        .cc(List.of())
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
