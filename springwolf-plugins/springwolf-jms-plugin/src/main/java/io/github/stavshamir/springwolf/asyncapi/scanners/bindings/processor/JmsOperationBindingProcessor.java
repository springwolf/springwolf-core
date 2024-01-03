// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;

public class JmsOperationBindingProcessor extends AbstractOperationBindingProcessor<JmsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(JmsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new JMSOperationBinding());
    }
}
