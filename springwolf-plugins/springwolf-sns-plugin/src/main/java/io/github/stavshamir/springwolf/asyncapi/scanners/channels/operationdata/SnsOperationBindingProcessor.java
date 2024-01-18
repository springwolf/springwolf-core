// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.processor.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SnsAsyncOperationBindingIdentifier;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingConsumer;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingIdentifier;

import java.util.List;

public class SnsOperationBindingProcessor extends AbstractOperationBindingProcessor<SnsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SnsAsyncOperationBinding bindingAnnotation) {
        var identifier = convertAnnotation(bindingAnnotation.endpoint());
        var protocol = readProtocol(bindingAnnotation.protocol());

        var consumer = SNSOperationBindingConsumer.builder()
                .protocol(protocol)
                .endpoint(identifier)
                .rawMessageDelivery(bindingAnnotation.rawMessageDelivery())
                .build();
        var snsOperationBinding =
                SNSOperationBinding.builder().consumers(List.of(consumer)).build();
        return new ProcessedOperationBinding(bindingAnnotation.type(), snsOperationBinding);
    }

    private SNSOperationBindingConsumer.Protocol readProtocol(String protocol) {
        return SNSOperationBindingConsumer.Protocol.valueOf(protocol.toUpperCase());
    }

    private SNSOperationBindingIdentifier convertAnnotation(SnsAsyncOperationBindingIdentifier identifier) {
        var builder = SNSOperationBindingIdentifier.builder();

        if (!identifier.url().isBlank()) {
            builder.url(identifier.url());
        }
        if (!identifier.arn().isBlank()) {
            builder.arn(identifier.arn());
        }
        if (!identifier.name().isBlank()) {
            builder.name(identifier.name());
        }
        if (!identifier.email().isBlank()) {
            builder.email(identifier.email());
        }
        if (!identifier.phone().isBlank()) {
            builder.phone(identifier.phone());
        }

        return builder.build();
    }
}
