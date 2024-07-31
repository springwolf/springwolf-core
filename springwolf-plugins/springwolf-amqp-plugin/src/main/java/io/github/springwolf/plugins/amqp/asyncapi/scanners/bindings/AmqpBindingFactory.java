// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

public class AmqpBindingFactory implements BindingFactory<RabbitListener>, EmbeddedValueResolverAware {
    private final RabbitListenerUtilContext context;
    private StringValueResolver stringValueResolver;

    public AmqpBindingFactory(List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        this.context = RabbitListenerUtilContext.create(queues, exchanges, bindings);
    }

    @Override
    public String getChannelName(RabbitListener annotation) {
        return RabbitListenerUtil.getChannelName(annotation, stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildChannelBinding(annotation, stringValueResolver, context);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildOperationBinding(annotation, stringValueResolver, context);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(RabbitListener annotation, SchemaObject headerSchema) {
        return RabbitListenerUtil.buildMessageBinding();
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.stringValueResolver = resolver;
    }
}
