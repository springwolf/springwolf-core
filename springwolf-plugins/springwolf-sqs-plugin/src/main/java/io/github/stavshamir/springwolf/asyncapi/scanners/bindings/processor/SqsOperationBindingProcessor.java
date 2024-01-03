// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sqs.SQSOperationBinding;

public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SqsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new SQSOperationBinding());
    }
}
