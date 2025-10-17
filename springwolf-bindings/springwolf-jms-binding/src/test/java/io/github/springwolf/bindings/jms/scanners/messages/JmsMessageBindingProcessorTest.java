// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.jms.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSMessageBinding;
import io.github.springwolf.bindings.jms.annotations.JmsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class JmsMessageBindingProcessorTest {
    private final JmsMessageBindingProcessor processor = new JmsMessageBindingProcessor();

    @Test
    void processTest() throws Exception {
        Method method = JmsMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("jms");
        assertThat(binding.getBinding()).isEqualTo(new JMSMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws Exception {
        Method method = JmsMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @JmsAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
