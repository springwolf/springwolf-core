// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor;

import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding.KafkaAsyncMessageBinding;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
@Order(BindingProcessorPriority.PROTOCOL_BINDING)
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

    private Schema<?> resolveSchemaOrNull(KafkaAsyncMessageBinding messageBinding) {
        Schema<?> schemaDefinition = null;
        switch (messageBinding.key().type()) {
            case UNDEFINED_KEY:
                break;
            case STRING_KEY:
                schemaDefinition = new StringSchema()
                        .example(messageBinding.key().example())
                        .description(resolveOrNull(messageBinding.key().description()));
        }

        return schemaDefinition;
    }
}
