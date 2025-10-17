// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class KafkaMessageBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock();
    private final KafkaMessageBindingProcessor processor = new KafkaMessageBindingProcessor(stringValueResolver);

    @Test
    void processTest() throws Exception {
        Method method = KafkaMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("kafka");
        assertThat(binding.getBinding()).isEqualTo(new KafkaMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws Exception {
        Method method = KafkaMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @KafkaAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
