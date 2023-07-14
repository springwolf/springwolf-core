package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class RabbitListenerUtil {

    public static String getChannelName(RabbitListener annotation, StringValueResolver resolver) {
        if (annotation.queues().length > 0) {
            return getChannelNameFromQueues(annotation, resolver);
        }

        return getChannelNameFromBindings(annotation, resolver);
    }

    private static String getChannelNameFromQueues(RabbitListener annotation, StringValueResolver resolver) {
        return Arrays.stream(annotation.queues())
                .map(resolver::resolveStringValue)
                .peek(queue -> log.debug("Resolved queue name: {}", queue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue name was found in @RabbitListener annotation"));
    }

    private static String getChannelNameFromBindings(RabbitListener annotation, StringValueResolver resolver) {
        return Arrays.stream(annotation.bindings())
                .map(binding -> binding.value().name())
                .map(resolver::resolveStringValue)
                .peek(queue -> log.debug("Resolved queue name: {}", queue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue name was found in @RabbitListener annotation"));
    }

    public static Map<String, ? extends ChannelBinding> buildChannelBinding(RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        AMQPChannelBinding.ExchangeProperties exchangeProperties = new AMQPChannelBinding.ExchangeProperties();
        exchangeProperties.setName(getExchangeName(annotation, resolver, bindingsMap));

        AMQPChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(exchangeProperties)
                .build();
        return Map.of("amqp", channelBinding);
    }

    private static String getExchangeName(RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        String exchangeName = Stream.of(annotation.bindings())
                .map(binding -> binding.exchange().name())
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);
        if (exchangeName == null && bindingsMap.containsKey(getChannelName(annotation, resolver))) {
            Binding binding = bindingsMap.get(getChannelName(annotation, resolver));
            exchangeName = binding.getExchange();
        }
        if (exchangeName == null) {
            exchangeName = "";
        }

        return exchangeName;
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        return Map.of("amqp", AMQPOperationBinding.builder().cc(getRoutingKeys(annotation, resolver, bindingsMap)).build());
    }

    private static List<String> getRoutingKeys(RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        /*
            The routing key is taken from the binding. As the key field in the @QueueBinding can be an empty array,
            it is set as an empty String in that case.
            Also in the case when there is no binding for the queue present at all, it means it uses the fact that
            RabbitMQ automatically binds default exchange to a queue with queue's name as a routing key.
         */
        List<String> routingKeys = Stream.of(annotation.bindings())
                .map(binding -> {
                    if (binding.key().length == 0) {
                        return Collections.singletonList("");
                    }

                    return Arrays.asList(binding.key());
                })
                .findFirst()
                .orElse(null);
        if (routingKeys == null && bindingsMap.containsKey(getChannelName(annotation, resolver))) {
            Binding binding = bindingsMap.get(getChannelName(annotation, resolver));
            routingKeys = Collections.singletonList(binding.getRoutingKey());
        }

        String exchangeName = getExchangeName(annotation, resolver, bindingsMap);
        if (exchangeName.isEmpty() && routingKeys == null) {
            routingKeys = Collections.singletonList(getChannelName(annotation, resolver));
        }

        return routingKeys;
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        // currently the feature to define amqp message binding is not implemented.
        return Map.of("amqp", new AMQPMessageBinding());
    }
}
