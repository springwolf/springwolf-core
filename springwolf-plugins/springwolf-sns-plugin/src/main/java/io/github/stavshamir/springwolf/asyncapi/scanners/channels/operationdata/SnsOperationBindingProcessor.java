// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingConsumer;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingIdentifier;

import java.util.List;

public class SnsOperationBindingProcessor extends AbstractOperationBindingProcessor<SnsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SnsAsyncOperationBinding bindingAnnotation) {
        // FIXME: Read these values from the annotation
        var consumer = SNSOperationBindingConsumer.builder()
                .protocol(SNSOperationBindingConsumer.Protocol.SQS)
                .endpoint(SNSOperationBindingIdentifier.builder().build())
                .rawMessageDelivery(true)
                .build();
        var snsOperationBinding =
                SNSOperationBinding.builder().consumers(List.of(consumer)).build();
        return new ProcessedOperationBinding(bindingAnnotation.type(), snsOperationBinding);
    }
}
