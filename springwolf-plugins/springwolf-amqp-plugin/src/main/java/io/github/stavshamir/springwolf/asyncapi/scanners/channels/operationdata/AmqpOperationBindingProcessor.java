// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.annotation.BindingProcessorPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AmqpAsyncOperationBinding;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(BindingProcessorPriority.PROTOCOL_BINDING)
public class AmqpOperationBindingProcessor extends AbstractOperationBindingProcessor<AmqpAsyncOperationBinding> {
    @Override
    protected ProcessedOperationBinding mapToOperationBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPOperationBinding amqpOperationBinding = AMQPOperationBinding.builder()
                .expiration(bindingAnnotation.expiration())
                .cc(Arrays.stream(bindingAnnotation.cc())
                        .map(this::resolveOrNull)
                        .toList())
                .priority(bindingAnnotation.priority())
                .deliveryMode(bindingAnnotation.deliveryMode())
                .mandatory(bindingAnnotation.mandatory())
                .timestamp(bindingAnnotation.timestamp())
                .ack(bindingAnnotation.ack())
                .build();

        return new ProcessedOperationBinding(bindingAnnotation.type(), amqpOperationBinding);
    }
}
