package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.kafka.KafkaOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.OperationBindingProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
public class KafkaOperationBindingProcessor implements OperationBindingProcessor {

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof KafkaAsyncOperationBinding)
                .map(annotation -> (KafkaAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToOperationBinding);
    }

    private ProcessedOperationBinding mapToOperationBinding(KafkaAsyncOperationBinding bindingAnnotation) {
        KafkaOperationBinding kafkaOperationBinding = KafkaOperationBinding.builder()
                .bindingVersion(nullIfEmpty(bindingAnnotation.bindingVersion()))
                .clientId(nullIfEmpty(bindingAnnotation.clientId()))
                .groupId(nullIfEmpty(bindingAnnotation.groupId()))
                .build();

        return new ProcessedOperationBinding(bindingAnnotation.type(), kafkaOperationBinding);
    }

    private static String nullIfEmpty(String stringValue) {
        return StringUtils.isEmpty(stringValue) ? null : stringValue;
    }
}
