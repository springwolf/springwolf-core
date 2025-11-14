// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.producer;

import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.AsyncAPI;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.AsyncApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class SpringwolfAmqpProducer {

    private final AsyncApiService asyncApiService;
    private final Map<String, RabbitTemplate> rabbitTemplateRegistry;

    public boolean isEnabled() {
        return !rabbitTemplateRegistry.isEmpty();
    }

    public SpringwolfAmqpProducer(AsyncApiService asyncApiService, List<RabbitTemplate> rabbitTemplates) {
        this.asyncApiService = asyncApiService;
        this.rabbitTemplateRegistry = buildRabbitTemplateRegistry(rabbitTemplates);
    }

    private Map<String, RabbitTemplate> buildRabbitTemplateRegistry(List<RabbitTemplate> templates) {
        return templates.stream()
                .collect(Collectors.toMap(RabbitTemplate::getRoutingKey, rabbitTemplate -> rabbitTemplate));
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

        Optional<RabbitTemplate> rabbitTemplate = getRabbitTemplate(routingKey);
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

    private Optional<RabbitTemplate> getRabbitTemplate(String routingKey) {
        return Optional.ofNullable(rabbitTemplateRegistry.get(routingKey))
                .or(() -> rabbitTemplateRegistry.values().stream().findFirst());
    }
}
