// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.message.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Order(value = BindingProcessorPriority.MANUAL_DEFINED)
public class TestMessageBindingProcessor implements MessageBindingProcessor {

    public static final String TYPE = "testType";
    public static final MessageBinding BINDING = new MessageBinding();

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof TestOperationBindingProcessor.TestOperationBinding)
                .map(annotation -> (TestOperationBindingProcessor.TestOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(
            TestOperationBindingProcessor.TestOperationBinding bindingAnnotation) {
        return new ProcessedMessageBinding(TYPE, BINDING);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD})
    public @interface TestMessageBinding {}
}
