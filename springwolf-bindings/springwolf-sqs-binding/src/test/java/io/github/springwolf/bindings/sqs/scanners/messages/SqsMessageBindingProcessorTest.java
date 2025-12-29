// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SqsMessageBindingProcessorTest {
    private final SqsMessageBindingProcessor processor = new SqsMessageBindingProcessor();

    @Test
    void processTest() throws Exception {
        Method method = SqsMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.type()).isEqualTo("sqs");
        assertThat(binding.binding()).isEqualTo(new SQSMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws Exception {
        Method method = SqsMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @SqsAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
