// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.operations;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.AbstractOperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.ProcessedOperationBinding;
import io.github.springwolf.plugins.amqp.asyncapi.annotations.AmqpAsyncOperationBinding;

import java.util.Arrays;

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

        return new ProcessedOperationBinding("amqp", amqpOperationBinding);
    }
}
