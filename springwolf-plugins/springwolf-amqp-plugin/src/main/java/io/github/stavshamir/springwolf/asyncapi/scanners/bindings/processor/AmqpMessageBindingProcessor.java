// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class AmqpMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof AmqpAsyncOperationBinding)
                .map(annotation -> (AmqpAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPMessageBinding amqpMessageBinding = AMQPMessageBinding.builder().build();

        return new ProcessedMessageBinding(bindingAnnotation.type(), amqpMessageBinding);
    }
}
