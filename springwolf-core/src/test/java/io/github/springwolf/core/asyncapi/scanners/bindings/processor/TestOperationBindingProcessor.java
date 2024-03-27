// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

@Order(value = BindingProcessorPriority.MANUAL_DEFINED)
public class TestOperationBindingProcessor implements OperationBindingProcessor {
    public static final String TYPE = "testType";
    public static final OperationBinding BINDING = new TestOperationBindingBinding();

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        if (method.isAnnotationPresent(TestOperationBinding.class)) {
            return Optional.of(new ProcessedOperationBinding(TYPE, BINDING));
        }
        return Optional.empty();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Inherited
    public @interface TestOperationBinding {
        TestMessageBindingProcessor.TestMessageBinding operationBinding() default
                @TestMessageBindingProcessor.TestMessageBinding();
    }

    public static class TestOperationBindingBinding extends OperationBinding {
        public TestOperationBindingBinding() {
            this.extensionFields = new HashMap<>();
            this.extensionFields.put("type", this.getClass().getName());
        }
    }
}
