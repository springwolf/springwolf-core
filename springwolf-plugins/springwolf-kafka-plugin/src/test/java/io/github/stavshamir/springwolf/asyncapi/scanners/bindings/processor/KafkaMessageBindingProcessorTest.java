// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaMessageBindingProcessorTest {
    private final KafkaMessageBindingProcessor processor = new KafkaMessageBindingProcessor();

    @Test
    void processTest() throws NoSuchMethodException {
        Method method = KafkaMessageBindingProcessorTest.class.getMethod("methodWithAnnotation");

        ProcessedMessageBinding binding = processor.process(method).get();

        assertThat(binding.getType()).isEqualTo("kafka");
        assertThat(binding.getBinding()).isEqualTo(new KafkaMessageBinding());
    }

    @Test
    void processWithoutAnnotationTest() throws NoSuchMethodException {
        Method method = KafkaMessageBindingProcessorTest.class.getMethod("methodWithoutAnnotation");

        Optional<ProcessedMessageBinding> binding = processor.process(method);

        assertThat(binding).isNotPresent();
    }

    @KafkaAsyncOperationBinding
    public void methodWithAnnotation() {}

    public void methodWithoutAnnotation() {}
}
