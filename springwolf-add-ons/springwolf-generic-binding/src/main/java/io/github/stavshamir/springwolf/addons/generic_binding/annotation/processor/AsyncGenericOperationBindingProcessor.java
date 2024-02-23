// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.addons.generic_binding.annotation.processor;

import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.addons.generic_binding.annotation.AsyncGenericOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;

import java.util.HashMap;
import java.util.Map;

public class AsyncGenericOperationBindingProcessor
        extends AbstractOperationBindingProcessor<AsyncGenericOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(AsyncGenericOperationBinding bindingAnnotation) {
        Map<String, Object> bindingData = PropertiesUtil.toMap(bindingAnnotation.fields());

        return new ProcessedOperationBinding(
                bindingAnnotation.type(), new DefaultAsyncGenerialOperationBinding(bindingData));
    }

    public static class DefaultAsyncGenerialOperationBinding extends OperationBinding {
        public DefaultAsyncGenerialOperationBinding(Map<String, Object> properties) {
            this.extensionFields = new HashMap<>(properties);
        }
    }
}
