// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncChannelBinding;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ProcessedChannelBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class GooglePubSubChannelBindingProcessor implements ChannelBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedChannelBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(GooglePubSubAsyncChannelBinding.class::isInstance)
                .map(GooglePubSubAsyncChannelBinding.class::cast)
                .findAny()
                .map(this::mapToChannelBinding);
    }

    private ProcessedChannelBinding mapToChannelBinding(GooglePubSubAsyncChannelBinding bindingAnnotation) {
        return new ProcessedChannelBinding(
                bindingAnnotation.type(),
                new GooglePubSubChannelBinding(
                        null,
                        null,
                        null,
                        new GooglePubSubSchemaSettings(
                                bindingAnnotation.schemaSettings().encoding(),
                                bindingAnnotation.schemaSettings().firstRevisionId(),
                                bindingAnnotation.schemaSettings().lastRevisionId(),
                                bindingAnnotation.schemaSettings().name()),
                        bindingAnnotation.bindingVersion()));
    }
}
