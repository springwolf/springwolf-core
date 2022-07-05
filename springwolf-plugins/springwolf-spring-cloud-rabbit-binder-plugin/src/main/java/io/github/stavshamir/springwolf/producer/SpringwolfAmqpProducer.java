package io.github.stavshamir.springwolf.producer;

import com.asyncapi.v2.binding.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.amqp.AMQPOperationBinding;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.ChannelsService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class SpringwolfAmqpProducer {

    private final ChannelsService channelsService;
    private final RabbitTemplate rabbitTemplate;

    @Getter
    private boolean isEnabled = true;

    public SpringwolfAmqpProducer(ChannelsService channelsService, List<RabbitTemplate> rabbitTemplates) {
        this.channelsService = channelsService;
        this.rabbitTemplate = rabbitTemplates.get(0);
    }

    public void send(String channelName, Object payload) {
        ChannelItem channelItem = channelsService.getChannels().get(channelName);

        String exchange = getExchangeName(channelItem);
        String routingKey = getRoutingKey(channelItem);
        if (routingKey.isEmpty() && exchange.isEmpty()) {
            routingKey = channelName;
        }

        MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
        org.springframework.amqp.core.Message message = messageConverter.toMessage(payload, new MessageProperties());
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    private String getExchangeName(ChannelItem channelItem) {
        String exchange = "";
        if (channelItem.getBindings() != null && channelItem.getBindings().containsKey("amqp")) {
            AMQPChannelBinding channelBinding = (AMQPChannelBinding) channelItem.getBindings().get("amqp");
            if (channelBinding.getExchange() != null && channelBinding.getExchange().getName() != null) {
                exchange = channelBinding.getExchange().getName();
            }
        }

        return exchange;
    }

    private String getRoutingKey(ChannelItem channelItem) {
        String routingKey = "";
        Operation operation = channelItem.getSubscribe() != null ? channelItem.getSubscribe() : channelItem.getPublish();
        if (operation != null && operation.getBindings() != null && operation.getBindings().containsKey("amqp")) {
            AMQPOperationBinding operationBinding = (AMQPOperationBinding) operation.getBindings().get("amqp");
            if (!CollectionUtils.isEmpty(operationBinding.getCc())) {
                routingKey = operationBinding.getCc().get(0);
            }
        }

        return routingKey;
    }

}
