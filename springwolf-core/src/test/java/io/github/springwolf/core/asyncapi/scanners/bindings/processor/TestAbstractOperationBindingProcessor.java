// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
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
