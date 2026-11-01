// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.util.List;
import java.util.Map;

public class AmqpBindingFactory implements BindingFactory<RabbitListener> {
    private final RabbitListenerUtilContext context;
    private final StringValueResolver stringValueResolver;

    public AmqpBindingFactory(
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings,
            StringValueResolver stringValueResolver) {
        this.context = RabbitListenerUtilContext.create(queues, exchanges, bindings);
        this.stringValueResolver = stringValueResolver;
    }

    @Override
    public String getChannelName(BindingContext<RabbitListener> bindingContext) {
        return RabbitListenerUtil.getChannelName(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public String getChannelId(BindingContext<RabbitListener> bindingContext) {
        return RabbitListenerUtil.getChannelId(bindingContext.annotation(), stringValueResolver);
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(BindingContext<RabbitListener> bindingContext) {
        return RabbitListenerUtil.buildChannelBinding(bindingContext.annotation(), stringValueResolver, context);
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(BindingContext<RabbitListener> bindingContext) {
        return RabbitListenerUtil.buildOperationBinding(bindingContext.annotation(), stringValueResolver, context);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(
            BindingContext<RabbitListener> bindingContext, SchemaObject headerSchema) {
        return RabbitListenerUtil.buildMessageBinding();
    }
}
