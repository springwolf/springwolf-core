// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

@Order(BindingProcessorPriority.MANUAL_DEFINED)
public class TestAbstractOperationBindingProcessor
        extends AbstractOperationBindingProcessor<TestAbstractOperationBindingProcessor.TestOperationBinding> {
    public static final String TYPE = "testType";
    public static final OperationBinding BINDING = new AbstractOperationBindingBinding();

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(TestOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(TYPE, BINDING);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD})
    @AsyncOperationBinding
    public @interface TestOperationBinding {
        TestMessageBindingProcessor.TestMessageBinding operationBinding() default
                @TestMessageBindingProcessor.TestMessageBinding();
    }

    public static class AbstractOperationBindingBinding extends OperationBinding {
        public AbstractOperationBindingBinding() {
            this.extensionFields = new HashMap<>();
            this.extensionFields.put("type", this.getClass().getName());
        }
    }
}
