// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSChannelBindingQueue;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    public SqsOperationBindingProcessor(StringValueResolver stringValueResolver) {
        super(stringValueResolver);
    }

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
        return new ProcessedOperationBinding("sqs", operationBinding);
    }
}
