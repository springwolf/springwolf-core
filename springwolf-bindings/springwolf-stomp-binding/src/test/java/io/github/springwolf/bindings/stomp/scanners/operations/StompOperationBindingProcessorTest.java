// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.stomp.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.stomp.StompOperationBinding;
import io.github.springwolf.bindings.stomp.annotations.StompAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StompOperationBindingProcessorTest {
    private final StompOperationBindingProcessor processor = new StompOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        StompAsyncOperationBinding annotation = StompOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(StompAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        var expectedOperation = StompOperationBinding.builder().build();

        assertThat(binding.getType()).isEqualTo("stomp");
        assertThat(binding.getBinding()).isEqualTo(expectedOperation);
    }

    @StompAsyncOperationBinding()
    public void methodWithAnnotation() {}
}
