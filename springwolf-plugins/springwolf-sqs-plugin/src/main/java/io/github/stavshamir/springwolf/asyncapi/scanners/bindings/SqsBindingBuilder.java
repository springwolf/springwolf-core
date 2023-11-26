// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SqsListenerUtil;
import lombok.NoArgsConstructor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.util.Map;

@NoArgsConstructor
public class SqsBindingBuilder implements BindingBuilder<SqsListener>, EmbeddedValueResolverAware {
    private StringValueResolver stringValueResolver;

    @Override
    public String getChannelName(SqsListener annotation) {
        return SqsListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ? extends ChannelBinding> buildChannelBinding(SqsListener annotation) {
        return SqsListenerUtil.buildChannelBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ? extends OperationBinding> buildOperationBinding(SqsListener annotation) {
        return SqsListenerUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ? extends MessageBinding> buildMessageBinding(SqsListener annotation) {
        return SqsListenerUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
