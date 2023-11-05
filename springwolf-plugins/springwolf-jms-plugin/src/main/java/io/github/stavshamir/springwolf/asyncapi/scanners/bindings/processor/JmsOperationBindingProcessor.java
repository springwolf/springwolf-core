// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.operation.jms.JMSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;

public class JmsOperationBindingProcessor extends AbstractOperationBindingProcessor<JmsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(JmsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new JMSOperationBinding());
    }
}
