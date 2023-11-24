// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.sns.SNSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SnsOperationBindingProcessorTest {
    private final SnsOperationBindingProcessor processor = new SnsOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        SnsAsyncOperationBinding annotation = SnsOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(SnsAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        assertThat(binding.getType()).isEqualTo("sns");
        assertThat(binding.getBinding()).isEqualTo(new SNSOperationBinding());
    }

    @SnsAsyncOperationBinding
    public void methodWithAnnotation() {}
}
