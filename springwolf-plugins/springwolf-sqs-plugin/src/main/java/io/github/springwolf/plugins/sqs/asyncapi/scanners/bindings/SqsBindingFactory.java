// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.asyncapi.scanners.bindings;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class SqsBindingFactory implements BindingFactory<SqsListener> {
    private final StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(BindingContext<SqsListener> bindingContext) {
        return SqsListenerUtil.getChannelName(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(BindingContext<SqsListener> bindingContext) {
        return SqsListenerUtil.buildChannelBinding(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(BindingContext<SqsListener> bindingContext) {
        return SqsListenerUtil.buildOperationBinding(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(
            BindingContext<SqsListener> bindingContext, SchemaObject headerSchema) {
        return SqsListenerUtil.buildMessageBinding();
    }
}
