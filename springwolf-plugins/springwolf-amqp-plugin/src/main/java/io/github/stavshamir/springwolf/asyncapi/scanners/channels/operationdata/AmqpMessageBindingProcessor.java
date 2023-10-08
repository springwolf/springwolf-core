// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.MessageBindingProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(BindingProcessorPriority.PROTOCOL_BINDING)
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
