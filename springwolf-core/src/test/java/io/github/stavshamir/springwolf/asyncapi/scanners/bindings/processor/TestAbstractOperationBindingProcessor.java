// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

@Order(value = BindingProcessorPriority.MANUAL_DEFINED)
public class TestAbstractOperationBindingProcessor
        extends AbstractOperationBindingProcessor<TestAbstractOperationBindingProcessor.TestOperationBinding> {
    public static final String TYPE = "testType";
    public static final OperationBinding BINDING = new AbstractOperationBindingBinding();

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(TestOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(TYPE, BINDING);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @AsyncOperationBinding
    @Inherited
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
