// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;

import java.util.List;

public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SqsAsyncOperationBinding bindingAnnotation) {
        // FIXME: Read the values from the annotation
        var queue = SQSChannelBindingQueue.builder()
                .name("queue-name")
                .fifoQueue(true)
                .build();
        var operationBinding =
                SQSOperationBinding.builder().queues(List.of(queue)).build();
        return new ProcessedOperationBinding(bindingAnnotation.type(), operationBinding);
    }
}
