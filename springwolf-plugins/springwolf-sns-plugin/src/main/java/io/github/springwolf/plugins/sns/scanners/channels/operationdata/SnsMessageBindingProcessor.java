// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.scanners.channels.operationdata;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.springwolf.plugins.sns.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class SnsMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(SnsAsyncOperationBinding.class::isInstance)
                .map(SnsAsyncOperationBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(SnsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedMessageBinding(bindingAnnotation.type(), new SNSMessageBinding());
    }
}
