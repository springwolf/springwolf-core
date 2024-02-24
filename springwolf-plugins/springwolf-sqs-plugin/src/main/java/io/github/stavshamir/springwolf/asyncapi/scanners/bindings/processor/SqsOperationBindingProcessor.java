// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncQueueBinding;

import java.util.ArrayList;
import java.util.List;

public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SqsAsyncOperationBinding bindingAnnotation) {
        List<SQSChannelBindingQueue> queues = new ArrayList<>();
        for (SqsAsyncQueueBinding queue : bindingAnnotation.queues()) {
            queues.add(SQSChannelBindingQueue.builder()
                    .name(queue.name())
                    .fifoQueue(queue.fifoQueue())
                    .build());
        }
        var operationBinding = SQSOperationBinding.builder().queues(queues).build();
        return new ProcessedOperationBinding(bindingAnnotation.type(), operationBinding);
    }
}
