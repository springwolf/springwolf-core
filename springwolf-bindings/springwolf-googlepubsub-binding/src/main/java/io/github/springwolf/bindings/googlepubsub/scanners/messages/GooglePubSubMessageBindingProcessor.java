// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchema;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class GooglePubSubMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(GooglePubSubAsyncMessageBinding.class::isInstance)
                .map(GooglePubSubAsyncMessageBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(GooglePubSubAsyncMessageBinding bindingAnnotation) {
        GooglePubSubSchema.GooglePubSubSchemaBuilder googlePubSubSchemaBuilder = GooglePubSubSchema.builder();
        if (StringUtils.isNotBlank(bindingAnnotation.schema().name())) {
            googlePubSubSchemaBuilder.name(bindingAnnotation.schema().name());
        }
        GooglePubSubMessageBinding.GooglePubSubMessageBindingBuilder bindingBuilder =
                GooglePubSubMessageBinding.builder().schema(googlePubSubSchemaBuilder.build());
        if (StringUtils.isNotBlank(bindingAnnotation.orderingKey())) {
            bindingBuilder.orderingKey(bindingAnnotation.orderingKey());
        }
        if (StringUtils.isNotBlank(bindingAnnotation.bindingVersion())) {
            bindingBuilder.bindingVersion(bindingAnnotation.bindingVersion());
        }
        return new ProcessedMessageBinding(bindingAnnotation.type(), bindingBuilder.build());
    }
}
