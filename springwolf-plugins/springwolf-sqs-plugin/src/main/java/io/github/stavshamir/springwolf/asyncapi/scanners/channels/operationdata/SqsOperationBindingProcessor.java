// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(BindingProcessorPriority.PROTOCOL_BINDING)
public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SqsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new SQSOperationBinding());
    }
}
