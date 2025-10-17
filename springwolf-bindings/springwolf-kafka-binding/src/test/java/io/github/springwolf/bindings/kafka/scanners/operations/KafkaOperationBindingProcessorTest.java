// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class KafkaOperationBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final KafkaOperationBindingProcessor processor = new KafkaOperationBindingProcessor(stringValueResolver);

    @Test
    void mapToOperationBindingTest() throws Exception {
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
