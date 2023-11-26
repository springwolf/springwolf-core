// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.RabbitListenerUtil;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

public class AmqpBindingBuilder implements BindingBuilder<RabbitListener>, EmbeddedValueResolverAware {
    private final RabbitListenerUtil.RabbitListenerUtilContext context;
    private StringValueResolver stringValueResolver;

    public AmqpBindingBuilder(List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        this.context = RabbitListenerUtil.RabbitListenerUtilContext.create(queues, exchanges, bindings);
    }

    @Override
    public String getChannelName(RabbitListener annotation) {
        return RabbitListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ? extends ChannelBinding> buildChannelBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, context);
    }

    @Override
    public Map<String, ? extends OperationBinding> buildOperationBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, context);
    }

    @Override
    public Map<String, ? extends MessageBinding> buildMessageBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
