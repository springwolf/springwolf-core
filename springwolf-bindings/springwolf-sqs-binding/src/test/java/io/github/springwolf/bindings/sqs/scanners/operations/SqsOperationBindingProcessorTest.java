// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
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

    @SqsAsyncOperationBinding(queues = {@SqsAsyncQueueBinding(name = "queue-name")})
    public void methodWithAnnotation() {}
}
