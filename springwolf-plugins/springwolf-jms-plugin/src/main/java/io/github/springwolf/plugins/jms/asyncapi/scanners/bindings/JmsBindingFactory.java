// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.jms.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class JmsBindingFactory implements BindingFactory<JmsListener> {
    private final StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(BindingContext<JmsListener> bindingContext) {
        return JmsListenerUtil.getChannelName(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(BindingContext<JmsListener> bindingContext) {
        return JmsListenerUtil.buildChannelBinding(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(BindingContext<JmsListener> bindingContext) {
        return JmsListenerUtil.buildOperationBinding(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(
            BindingContext<JmsListener> bindingContext, SchemaObject headerSchema) {
        return JmsListenerUtil.buildMessageBinding();
    }
}
