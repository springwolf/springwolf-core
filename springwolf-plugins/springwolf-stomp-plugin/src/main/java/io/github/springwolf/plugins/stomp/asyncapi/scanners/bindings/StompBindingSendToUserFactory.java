// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.SendToUserUtil;
import io.github.springwolf.plugins.stomp.asyncapi.scanners.common.SendToUtil;
import io.github.springwolf.plugins.stomp.configuration.properties.SpringwolfStompConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@RequiredArgsConstructor
public class StompBindingSendToUserFactory implements BindingFactory<SendToUser> {
    private final SpringwolfStompConfigProperties properties;
    private final StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(SendToUser annotation, Class<?> component) {
        return properties.getEndpoint().getUser() + SendToUserUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(SendToUser annotation) {
        return SendToUserUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(SendToUser annotation) {
        return SendToUserUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(SendToUser annotation, SchemaObject headerSchema) {
        return SendToUtil.buildMessageBinding();
    }
}
