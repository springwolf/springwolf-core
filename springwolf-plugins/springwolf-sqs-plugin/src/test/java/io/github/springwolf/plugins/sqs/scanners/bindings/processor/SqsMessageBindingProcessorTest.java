// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import io.github.springwolf.plugins.sqs.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SqsMessageBindingProcessorTest {
    private final SqsMessageBindingProcessor processor = new SqsMessageBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        Method method = SqsMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("sqs");
        assertThat(binding.getBinding()).isEqualTo(new SQSMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        Method method = SqsMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @SqsAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
