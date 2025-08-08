// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.EmptyChannelBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

@Order(value = BindingProcessorPriority.MANUAL_DEFINED)
public class TestChannelBindingProcessor implements ChannelBindingProcessor {

    public static final String TYPE = "testType";
    public static final ChannelBinding BINDING = new EmptyChannelBinding();

    @Override
    public Optional<ProcessedChannelBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(annotation -> annotation instanceof TestChannelBindingProcessor.TestChannelBinding)
                .map(annotation -> (TestChannelBindingProcessor.TestChannelBinding) annotation)
                .findAny()
                .map(this::mapToChannelBinding);
    }

    private ProcessedChannelBinding mapToChannelBinding(
            TestChannelBindingProcessor.TestChannelBinding bindingAnnotation) {
        return new ProcessedChannelBinding(TYPE, BINDING);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Inherited
    @AsyncChannelBinding
    public @interface TestChannelBinding {}
}
