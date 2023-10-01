package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.processor;

import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.AsyncGenericOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
// TODO: Add bindingPriority
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
