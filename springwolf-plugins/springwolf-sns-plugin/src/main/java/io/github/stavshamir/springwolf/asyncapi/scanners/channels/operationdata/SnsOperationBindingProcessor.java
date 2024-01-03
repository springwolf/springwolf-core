// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBinding;

public class SnsOperationBindingProcessor extends AbstractOperationBindingProcessor<SnsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SnsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new SNSOperationBinding());
    }
}
