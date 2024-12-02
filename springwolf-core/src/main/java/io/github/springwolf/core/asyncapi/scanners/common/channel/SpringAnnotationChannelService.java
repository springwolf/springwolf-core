// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class SpringAnnotationChannelService<Annotation extends java.lang.annotation.Annotation> {

    private final BindingFactory<Annotation> bindingFactory;

    public ChannelObject buildChannel(
            Annotation annotation, BindingContext bindingContext, Map<String, Message> messages) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(annotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        String channelName = bindingFactory.getChannelName(annotation, bindingContext);
        return ChannelObject.builder()
                .channelId(ReferenceUtil.toValidId(channelName))
                .address(channelName)
                .messages(messages)
                .bindings(chBinding)
                .build();
    }
}
