// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.producer;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
public class SpringwolfAmqpProducer {

    private final AsyncApiService asyncApiService;
    private final Optional<RabbitTemplate> rabbitTemplate;

    public boolean isEnabled() {
        return rabbitTemplate.isPresent();
    }

    public SpringwolfAmqpProducer(AsyncApiService asyncApiService, List<RabbitTemplate> rabbitTemplates) {
        this.asyncApiService = asyncApiService;
        this.rabbitTemplate = rabbitTemplates.isEmpty() ? Optional.empty() : Optional.of(rabbitTemplates.get(0));
    }

    public void send(String channelName, Object payload) {
        AsyncAPI asyncAPI = asyncApiService.getAsyncAPI();
        ChannelObject channelItem = asyncAPI.getChannels().get(channelName);

        Operation operation = null;
        if (asyncAPI.getOperations() != null) {
            operation = asyncAPI.getOperations().get("amqp");
        }

        String exchange = getExchangeName(channelItem);
        String routingKey = getRoutingKey(operation);
        if (routingKey.isEmpty() && exchange.isEmpty()) {
            routingKey = channelName;
        }

        if (rabbitTemplate.isPresent()) {
            rabbitTemplate.get().convertAndSend(exchange, routingKey, payload);
        } else {
            log.warn("AMQP producer is not configured");
        }
    }

    private String getExchangeName(ChannelObject channelItem) {
        String exchange = "";
        if (channelItem.getBindings() != null && channelItem.getBindings().containsKey("amqp")) {
            AMQPChannelBinding channelBinding =
                    (AMQPChannelBinding) channelItem.getBindings().get("amqp");
            if (channelBinding.getExchange() != null
                    && channelBinding.getExchange().getName() != null) {
                exchange = channelBinding.getExchange().getName();
            }
        }

        return exchange;
    }

    private String getRoutingKey(Operation operation) {
        String routingKey = "";
        if (operation != null
                && operation.getBindings() != null
                && operation.getBindings().containsKey("amqp")) {
            AMQPOperationBinding operationBinding =
                    (AMQPOperationBinding) operation.getBindings().get("amqp");
            if (!CollectionUtils.isEmpty(operationBinding.getCc())) {
                routingKey = operationBinding.getCc().get(0);
            }
        }

        return routingKey;
    }
}
