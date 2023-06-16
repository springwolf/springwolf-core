package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.message.kafka.KafkaMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding.KafkaAsyncMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.MessageBindingProcessor;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
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
        KafkaMessageBinding kafkaMessageBinding = KafkaMessageBinding.builder()
                .bindingVersion(resolveOrNull(messageBinding.bindingVersion()))
                .key(resolveSchemaOrNull(messageBinding))
                .build();

        return new ProcessedMessageBinding(bindingAnnotation.type(), kafkaMessageBinding);
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
