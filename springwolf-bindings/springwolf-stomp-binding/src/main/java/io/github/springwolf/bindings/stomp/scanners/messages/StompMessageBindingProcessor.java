// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.stomp.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.stomp.StompMessageBinding;
import io.github.springwolf.bindings.stomp.annotations.StompAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class StompMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(StompAsyncOperationBinding.class::isInstance)
                .map(StompAsyncOperationBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(StompAsyncOperationBinding bindingAnnotation) {
        StompMessageBinding stompMessageBinding = new StompMessageBinding();

        return new ProcessedMessageBinding("stomp", stompMessageBinding);
    }
}
