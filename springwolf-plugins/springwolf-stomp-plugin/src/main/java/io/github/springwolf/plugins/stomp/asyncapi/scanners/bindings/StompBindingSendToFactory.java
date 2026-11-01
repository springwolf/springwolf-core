// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.SendToUtil;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class StompBindingSendToFactory implements BindingFactory<SendTo> {
    private final SpringwolfStompConfigProperties properties;
    private final StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(BindingContext<SendTo> bindingContext) {
        return properties.getEndpoint().getApp()
                + SendToUtil.getChannelName(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(BindingContext<SendTo> bindingContext) {
        return SendToUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(BindingContext<SendTo> bindingContext) {
        return SendToUtil.buildOperationBinding(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(
            BindingContext<SendTo> bindingContext, SchemaObject headerSchema) {
        return SendToUtil.buildMessageBinding();
    }
}
