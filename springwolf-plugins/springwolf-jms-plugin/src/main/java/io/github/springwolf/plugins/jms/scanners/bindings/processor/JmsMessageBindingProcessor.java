// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.scanners.bindings.processor;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.springwolf.plugins.jms.scanners.channels.operationdata.annotation.JmsAsyncOperationBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class JmsMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof JmsAsyncOperationBinding)
                .map(annotation -> (JmsAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(JmsAsyncOperationBinding bindingAnnotation) {
        JMSMessageBinding jmsMessageBinding = new JMSMessageBinding();

        return new ProcessedMessageBinding(bindingAnnotation.type(), jmsMessageBinding);
    }
}
