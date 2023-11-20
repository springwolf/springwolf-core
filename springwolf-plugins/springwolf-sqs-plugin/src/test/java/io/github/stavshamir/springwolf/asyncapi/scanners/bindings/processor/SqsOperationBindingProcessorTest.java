// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SqsOperationBindingProcessorTest {
    private final SqsOperationBindingProcessor processor = new SqsOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        SqsAsyncOperationBinding annotation = SqsOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(SqsAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        assertThat(binding.getType()).isEqualTo("sqs");
        assertThat(binding.getBinding()).isEqualTo(new SQSOperationBinding());
    }

    @SqsAsyncOperationBinding
    public void methodWithAnnotation() {}
}
