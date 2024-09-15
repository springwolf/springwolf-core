// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.amqp.scanners.operations;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.bindings.amqp.annotations.AmqpAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;

import java.util.Arrays;

public class AmqpOperationBindingProcessor extends AbstractOperationBindingProcessor<AmqpAsyncOperationBinding> {
    @Override
    protected ProcessedOperationBinding mapToOperationBinding(AmqpAsyncOperationBinding bindingAnnotation) {
        AMQPOperationBinding amqpOperationBinding = AMQPOperationBinding.builder()
                .expiration(bindingAnnotation.expiration())
                .userId(resolveOrNull(bindingAnnotation.userId()))
                .cc(Arrays.stream(bindingAnnotation.cc())
                        .map(this::resolveOrNull)
                        .toList())
                .priority(bindingAnnotation.priority())
                .deliveryMode(bindingAnnotation.deliveryMode())
                .mandatory(bindingAnnotation.mandatory())
                .bcc(Arrays.stream(bindingAnnotation.bcc())
                        .map(this::resolveOrNull)
                        .toList())
                .timestamp(bindingAnnotation.timestamp())
                .ack(bindingAnnotation.ack())
                .build();

        return new ProcessedOperationBinding("amqp", amqpOperationBinding);
    }
}
