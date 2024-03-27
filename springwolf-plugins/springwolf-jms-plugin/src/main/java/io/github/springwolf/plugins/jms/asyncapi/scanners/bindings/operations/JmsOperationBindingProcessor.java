// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.asyncapi.scanners.bindings.operations;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.jms.asyncapi.annotations.JmsAsyncOperationBinding;

public class JmsOperationBindingProcessor extends AbstractOperationBindingProcessor<JmsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(JmsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new JMSOperationBinding());
    }
}
