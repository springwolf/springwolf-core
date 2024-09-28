// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.jms.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.jms.JMSMessageBinding;
import io.github.springwolf.bindings.jms.annotations.JmsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class JmsMessageBindingProcessor implements MessageBindingProcessor {

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(annotation -> annotation instanceof JmsAsyncOperationBinding)
                .map(annotation -> (JmsAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(JmsAsyncOperationBinding bindingAnnotation) {
        JMSMessageBinding jmsMessageBinding = new JMSMessageBinding();

        return new ProcessedMessageBinding("jms", jmsMessageBinding);
    }
}
