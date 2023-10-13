// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.message.sns.SNSMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
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
