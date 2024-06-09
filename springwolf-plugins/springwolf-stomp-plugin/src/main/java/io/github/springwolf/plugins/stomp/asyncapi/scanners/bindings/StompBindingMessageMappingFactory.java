// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.MessageMappingUtil;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class StompBindingMessageMappingFactory implements BindingFactory<MessageMapping>, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;
    private final SpringwolfStompConfigProperties properties;

    @Override
    public String getChannelName(MessageMapping annotation) {
        return properties.getEndpoint().getApp() + MessageMappingUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(MessageMapping annotation) {
        return MessageMappingUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(MessageMapping annotation) {
        return MessageMappingUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(MessageMapping annotation) {
        return MessageMappingUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
