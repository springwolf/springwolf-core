package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.OperationBindingProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AmqpOperationBindingProcessor implements OperationBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof AmqpAsyncOperationBinding)
                .map(annotation -> (AmqpAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToOperationBinding);
    }

    private ProcessedOperationBinding mapToOperationBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPOperationBinding amqpOperationBinding = AMQPOperationBinding.builder()
                .expiration(bindingAnnotation.expiration())
                .cc(Arrays.stream(bindingAnnotation.cc())
                        .map(this::resolveOrNull)
                        .collect(Collectors.toList()))
                .priority(bindingAnnotation.priority())
                .deliveryMode(bindingAnnotation.deliveryMode())
                .mandatory(bindingAnnotation.mandatory())
                .timestamp(bindingAnnotation.timestamp())
                .ack(bindingAnnotation.ack())
                .build();

        return new ProcessedOperationBinding(bindingAnnotation.type(), amqpOperationBinding);
    }

    private String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? resolver.resolveStringValue(stringValue) : null;
    }
}
