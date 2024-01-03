// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import org.junit.jupiter.api.Test;

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
                .isEqualTo(AMQPOperationBinding.builder().build());
        //                        new AMQPOperationBinding(0, null, List.of(), 0, 0, false, null, null, false, false,
        // "0.2.0")
    }

    @AmqpAsyncOperationBinding
    public void methodWithAnnotation() {}
}
