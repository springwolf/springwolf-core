// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding.KafkaAsyncMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.kafka.KafkaMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.Schema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
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
    public Optional<ProcessedMessageBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof KafkaAsyncOperationBinding)
                .map(annotation -> (KafkaAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToMessageBinding);
    }

    private ProcessedMessageBinding mapToMessageBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        KafkaAsyncMessageBinding messageBinding = bindingAnnotation.messageBinding();

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

    private Schema resolveSchemaOrNull(KafkaAsyncMessageBinding messageBinding) {
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
