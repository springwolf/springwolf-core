// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SqsOperationBindingProcessorTest {
    private final SqsOperationBindingProcessor processor = new SqsOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        SqsAsyncOperationBinding annotation = SqsOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(SqsAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        var expectedOperation = SQSOperationBinding.builder()
                .queues(List.of(SQSChannelBindingQueue.builder()
                        .name("queue-name")
                        .fifoQueue(true)
                        .build()))
                .build();

        assertThat(binding.getType()).isEqualTo("sqs");
        assertThat(binding.getBinding()).isEqualTo(expectedOperation);
    }

    @SqsAsyncOperationBinding
    public void methodWithAnnotation() {}
}
