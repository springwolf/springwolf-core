// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.jms.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import io.github.springwolf.bindings.jms.annotations.JmsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class JmsOperationBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final JmsOperationBindingProcessor processor = new JmsOperationBindingProcessor(stringValueResolver);

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        JmsAsyncOperationBinding annotation = JmsOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(JmsAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        assertThat(binding.getType()).isEqualTo("jms");
        assertThat(binding.getBinding()).isEqualTo(new JMSOperationBinding());
    }

    @JmsAsyncOperationBinding
    public void methodWithAnnotation() {}
}
