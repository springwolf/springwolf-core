// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.scanners.channels.operationdata;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import io.github.springwolf.plugins.sns.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.springwolf.plugins.sns.scanners.channels.operationdata.annotation.SnsAsyncOperationBindingIdentifier;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class SnsMessageBindingProcessorTest {
    private final SnsMessageBindingProcessor processor = new SnsMessageBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        Method method = SnsMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("sns");
        assertThat(binding.getBinding()).isEqualTo(new SNSMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        Method method = SnsMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @SnsAsyncOperationBinding(protocol = "sqs", endpoint = @SnsAsyncOperationBindingIdentifier())
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
