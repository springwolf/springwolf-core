// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sqs.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.sqs.SQSMessageBinding;
import io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class SqsMessageBindingProcessor implements MessageBindingProcessor {

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

        return new ProcessedMessageBinding("sqs", sqsMessageBinding);
    }
}
