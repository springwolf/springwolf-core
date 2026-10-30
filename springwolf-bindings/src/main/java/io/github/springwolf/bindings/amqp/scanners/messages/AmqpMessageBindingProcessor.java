// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.amqp.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.springwolf.bindings.amqp.annotations.AmqpAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class AmqpMessageBindingProcessor implements MessageBindingProcessor {
    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(AmqpAsyncOperationBinding.class::isInstance)
                .map(AmqpAsyncOperationBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPMessageBinding amqpMessageBinding = AMQPMessageBinding.builder().build();

        return new ProcessedMessageBinding("amqp", amqpMessageBinding);
    }
}
