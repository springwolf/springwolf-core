// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.SendToUtil;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class StompBindingSendToFactory implements BindingFactory<SendTo>, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;
    private final SpringwolfStompConfigProperties properties;

    @Override
    public String getChannelName(SendTo annotation) {
        return properties.getEndpoint().getApp() + SendToUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(SendTo annotation) {
        return SendToUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(SendTo annotation) {
        return SendToUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(SendTo annotation) {
        return SendToUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
