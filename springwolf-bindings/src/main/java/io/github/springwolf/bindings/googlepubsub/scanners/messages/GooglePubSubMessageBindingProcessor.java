// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.scanners.messages;

import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchema;
import io.github.springwolf.bindings.googlepubsub.annotations.GooglePubSubAsyncMessageBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Optional;

public class GooglePubSubMessageBindingProcessor implements MessageBindingProcessor {

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(GooglePubSubAsyncMessageBinding.class::isInstance)
                .map(GooglePubSubAsyncMessageBinding.class::cast)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(GooglePubSubAsyncMessageBinding bindingAnnotation) {
        GooglePubSubSchema.GooglePubSubSchemaBuilder googlePubSubSchemaBuilder = GooglePubSubSchema.builder();
        if (!bindingAnnotation.schema().name().isBlank()) {
            googlePubSubSchemaBuilder.name(bindingAnnotation.schema().name());
        }
        GooglePubSubMessageBinding.GooglePubSubMessageBindingBuilder bindingBuilder =
                GooglePubSubMessageBinding.builder().schema(googlePubSubSchemaBuilder.build());
        if (!bindingAnnotation.orderingKey().isBlank()) {
            bindingBuilder.orderingKey(bindingAnnotation.orderingKey());
        }
        if (!bindingAnnotation.bindingVersion().isBlank()) {
            bindingBuilder.bindingVersion(bindingAnnotation.bindingVersion());
        }
        return new ProcessedMessageBinding("googlepubsub", bindingBuilder.build());
    }
}
