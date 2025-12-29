// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.stomp.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.stomp.StompOperationBinding;
import io.github.springwolf.bindings.stomp.annotations.StompAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class StompOperationBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final StompOperationBindingProcessor processor = new StompOperationBindingProcessor(stringValueResolver);

    @Test
    void mapToOperationBindingTest() throws Exception {
        StompAsyncOperationBinding annotation = StompOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(StompAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        var expectedOperation = StompOperationBinding.builder().build();

        assertThat(binding.type()).isEqualTo("stomp");
        assertThat(binding.binding()).isEqualTo(expectedOperation);
    }

    @StompAsyncOperationBinding()
    public void methodWithAnnotation() {}
}
