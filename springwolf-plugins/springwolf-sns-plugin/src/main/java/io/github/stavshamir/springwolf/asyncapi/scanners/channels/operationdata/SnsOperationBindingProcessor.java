// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;

public class SnsOperationBindingProcessor extends AbstractOperationBindingProcessor<SnsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SnsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new SQSOperationBinding());
    }
}
