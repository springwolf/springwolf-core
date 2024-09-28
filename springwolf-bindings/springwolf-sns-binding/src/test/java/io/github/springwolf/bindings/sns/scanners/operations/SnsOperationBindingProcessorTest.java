// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sns.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingConsumer;
import io.github.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingIdentifier;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringValueResolver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class SnsOperationBindingProcessorTest {
    private final StringValueResolver stringValueResolver = mock(StringValueResolver.class);
    private final SnsOperationBindingProcessor processor = new SnsOperationBindingProcessor(stringValueResolver);

    @Test
    void mapToOperationBindingTest() throws NoSuchMethodException {
        SnsAsyncOperationBinding annotation = SnsOperationBindingProcessorTest.class
                .getMethod("methodWithAnnotation")
                .getAnnotation(SnsAsyncOperationBinding.class);

        ProcessedOperationBinding binding = processor.mapToOperationBinding(annotation);

        var expectedOperation = SNSOperationBinding.builder()
                .consumers(List.of(SNSOperationBindingConsumer.builder()
                        .protocol(SNSOperationBindingConsumer.Protocol.SQS)
                        .endpoint(SNSOperationBindingIdentifier.builder().build())
                        .rawMessageDelivery(true)
                        .build()))
                .build();

        assertThat(binding.getType()).isEqualTo("sns");
        assertThat(binding.getBinding()).isEqualTo(expectedOperation);
    }

    @SnsAsyncOperationBinding(protocol = "sqs", endpoint = @SnsAsyncOperationBindingIdentifier())
    public void methodWithAnnotation() {}
}
