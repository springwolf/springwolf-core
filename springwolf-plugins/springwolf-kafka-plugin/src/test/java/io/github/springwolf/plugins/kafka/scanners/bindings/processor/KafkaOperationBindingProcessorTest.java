// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.kafka.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaOperationBindingProcessorTest {
    private final KafkaOperationBindingProcessor processor = new KafkaOperationBindingProcessor();

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        KafkaAsyncOperationBinding annotation = KafkaOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(KafkaAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        assertThat(binding.getType()).isEqualTo("kafka");
        assertThat(binding.getBinding()).isEqualTo(new KafkaOperationBinding());
    }

    @KafkaAsyncOperationBinding
    public void methodWithAnnotation() {}
}
