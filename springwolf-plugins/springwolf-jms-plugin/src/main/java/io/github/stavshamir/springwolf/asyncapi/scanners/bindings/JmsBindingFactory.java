// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.JmsListenerUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import lombok.NoArgsConstructor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@NoArgsConstructor
public class JmsBindingFactory implements BindingFactory<JmsListener>, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(JmsListener annotation) {
        return JmsListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(JmsListener annotation) {
        return JmsListenerUtil.buildChannelBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(JmsListener annotation) {
        return JmsListenerUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(JmsListener annotation) {
        return JmsListenerUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
