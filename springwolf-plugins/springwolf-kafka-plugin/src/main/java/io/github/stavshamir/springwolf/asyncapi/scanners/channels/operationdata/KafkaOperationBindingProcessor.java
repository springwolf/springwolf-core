package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.kafka.KafkaOperationBinding;
import com.asyncapi.v2.schema.Schema;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.KafkaListenerUtil;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.OperationBindingProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
public class KafkaOperationBindingProcessor implements OperationBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof KafkaAsyncOperationBinding)
                .map(annotation -> (KafkaAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToOperationBinding);
    }

    private ProcessedOperationBinding mapToOperationBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        String clientId = resolveOrNull(bindingAnnotation.clientId());
        Schema clientIdSchema = KafkaListenerUtil.buildKafkaClientIdSchema(clientId);
        String groupId = resolveOrNull(bindingAnnotation.groupId());
        Schema groupIdSchema = KafkaListenerUtil.buildKafkaGroupIdSchema(groupId);

        KafkaOperationBinding kafkaOperationBinding = KafkaOperationBinding.builder()
                .bindingVersion(resolveOrNull(bindingAnnotation.bindingVersion()))
                .clientId(clientIdSchema)
                .groupId(groupIdSchema)
                .build();

        return new ProcessedOperationBinding(bindingAnnotation.type(), kafkaOperationBinding);
    }

    private String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? resolver.resolveStringValue(stringValue) : null;
    }
}
