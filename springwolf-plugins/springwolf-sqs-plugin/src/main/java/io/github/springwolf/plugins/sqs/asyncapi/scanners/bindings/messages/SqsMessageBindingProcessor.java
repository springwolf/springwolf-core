// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings.messages;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import io.github.springwolf.plugins.sqs.annotations.SqsAsyncOperationBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class SqsMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
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
