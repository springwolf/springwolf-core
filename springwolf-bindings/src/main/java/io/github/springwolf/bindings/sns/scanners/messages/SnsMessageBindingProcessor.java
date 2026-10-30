// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.sns.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSMessageBinding;
import io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class SnsMessageBindingProcessor implements MessageBindingProcessor {

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(SnsAsyncOperationBinding.class::isInstance)
                .map(SnsAsyncOperationBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(SnsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedMessageBinding("sns", new SNSMessageBinding());
    }
}
