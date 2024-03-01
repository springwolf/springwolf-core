// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.jms.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JmsOperationBindingProcessorTest {
    private final JmsOperationBindingProcessor processor = new JmsOperationBindingProcessor();

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
