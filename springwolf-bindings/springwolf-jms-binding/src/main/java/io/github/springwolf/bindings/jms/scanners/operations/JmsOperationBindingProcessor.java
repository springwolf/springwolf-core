// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.jms.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSOperationBinding;
import io.github.springwolf.bindings.jms.annotations.JmsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.springframework.util.StringValueResolver;

public class JmsOperationBindingProcessor extends AbstractOperationBindingProcessor<JmsAsyncOperationBinding> {
    public JmsOperationBindingProcessor(StringValueResolver stringValueResolver) {
        super(stringValueResolver);
    }

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(JmsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding("jms", new JMSOperationBinding());
    }
}
