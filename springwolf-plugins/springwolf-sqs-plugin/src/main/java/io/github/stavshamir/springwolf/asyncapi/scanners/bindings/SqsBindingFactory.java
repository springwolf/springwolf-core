// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SqsListenerUtil;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import lombok.NoArgsConstructor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@NoArgsConstructor
public class SqsBindingFactory implements BindingFactory<SqsListener>, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(SqsListener annotation) {
        return SqsListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(SqsListener annotation) {
        return SqsListenerUtil.buildChannelBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(SqsListener annotation) {
        return SqsListenerUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(SqsListener annotation) {
        return SqsListenerUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
