package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.OperationBindingProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@Component
public class AmqpOperationBindingProcessor implements OperationBindingProcessor {

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation instanceof AmqpAsyncOperationBinding)
                .map(annotation -> (AmqpAsyncOperationBinding) annotation)
                .findAny()
                .map(this::mapToOperationBinding);
    }

    private ProcessedOperationBinding mapToOperationBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPOperationBinding kafkaOperationBinding = AMQPOperationBinding.builder()
                .expiration(bindingAnnotation.expiration())
                .cc(Arrays.asList(bindingAnnotation.cc()))
                .priority(bindingAnnotation.priority())
                .deliveryMode(bindingAnnotation.deliveryMode())
                .mandatory(bindingAnnotation.mandatory())
                .timestamp(bindingAnnotation.timestamp())
                .ack(bindingAnnotation.ack())
                .build();

        return new ProcessedOperationBinding(bindingAnnotation.type(), kafkaOperationBinding);
    }
}
