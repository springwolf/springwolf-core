// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.springwolf.core.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class SqsMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(SqsAsyncOperationBinding.class::isInstance)
                .map(SqsAsyncOperationBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(SqsAsyncOperationBinding bindingAnnotation) {
        SQSMessageBinding sqsMessageBinding = new SQSMessageBinding();

        return new ProcessedMessageBinding(bindingAnnotation.type(), sqsMessageBinding);
    }
}
