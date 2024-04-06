// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings.messages;

import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.springwolf.asyncapi.v3.model.schema.Schema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.ProcessedMessageBinding;
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class KafkaMessageBindingProcessor implements MessageBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(annotation -> annotation instanceof KafkaAsyncOperationBinding)
                .map(annotation -> (KafkaAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        KafkaAsyncOperationBinding.KafkaAsyncMessageBinding messageBinding = bindingAnnotation.messageBinding();

        KafkaMessageBinding.KafkaMessageBindingBuilder kafkaMessageBindingBuilder = KafkaMessageBinding.builder();

        kafkaMessageBindingBuilder.key(resolveSchemaOrNull(messageBinding));

        String bindingVersion = resolveOrNull(messageBinding.bindingVersion());
        if (StringUtils.hasText(bindingVersion)) {
            kafkaMessageBindingBuilder.bindingVersion(bindingVersion);
        }

        return new ProcessedMessageBinding(bindingAnnotation.type(), kafkaMessageBindingBuilder.build());
    }

    private String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? resolver.resolveStringValue(stringValue) : null;
    }

    private Schema resolveSchemaOrNull(KafkaAsyncOperationBinding.KafkaAsyncMessageBinding messageBinding) {
        Schema schemaDefinition = null;
        switch (messageBinding.key().type()) {
            case UNDEFINED_KEY:
                break;
            case STRING_KEY:
                schemaDefinition = SchemaObject.builder()
                        .type("string")
                        .examples(List.of(messageBinding.key().example()))
                        .description(resolveOrNull(messageBinding.key().description()))
                        .build();
        }

        return schemaDefinition;
    }
}
