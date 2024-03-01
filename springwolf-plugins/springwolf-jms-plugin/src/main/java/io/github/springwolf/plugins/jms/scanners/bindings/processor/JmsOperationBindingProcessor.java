// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.plugins.jms.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;

public class JmsOperationBindingProcessor extends AbstractOperationBindingProcessor<JmsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(JmsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new JMSOperationBinding());
    }
}
