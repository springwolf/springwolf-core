// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.AbstractChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.channels.ProcessedChannelBinding;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

@Order(value = BindingProcessorPriority.MANUAL_DEFINED)
public class TestAbstractChannelBindingProcessor
        extends AbstractChannelBindingProcessor<TestAbstractChannelBindingProcessor.TestChannelBinding> {
    public static final String TYPE = "testType";
    public static final ChannelBinding BINDING = new AbstractChannelBindingBinding();

    public TestAbstractChannelBindingProcessor(StringValueResolver stringValueResolver) {
        super(stringValueResolver);
    }

    @Override
    protected ProcessedChannelBinding mapToChannelBinding(TestChannelBinding bindingAnnotation) {
        return new ProcessedChannelBinding(TYPE, BINDING);
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @AsyncChannelBinding
    @Inherited
    public @interface TestChannelBinding {
        TestChannelBindingProcessor.TestChannelBinding channelBinding() default
                @TestChannelBindingProcessor.TestChannelBinding();
    }

    public static class AbstractChannelBindingBinding extends ChannelBinding {
        public AbstractChannelBindingBinding() {
            this.extensionFields = new HashMap<>();
            this.extensionFields.put("type", this.getClass().getName());
        }
    }
}
