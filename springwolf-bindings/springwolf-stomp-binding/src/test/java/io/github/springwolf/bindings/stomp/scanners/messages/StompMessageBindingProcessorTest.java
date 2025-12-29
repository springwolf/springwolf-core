// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.stomp.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.stomp.StompMessageBinding;
import io.github.springwolf.bindings.stomp.annotations.StompAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class StompMessageBindingProcessorTest {
    private final StompMessageBindingProcessor processor = new StompMessageBindingProcessor();

    @Test
    void processTest() throws Exception {
        Method method = StompMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.type()).isEqualTo("stomp");
        assertThat(binding.binding()).isEqualTo(new StompMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws Exception {
        Method method = StompMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @StompAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
