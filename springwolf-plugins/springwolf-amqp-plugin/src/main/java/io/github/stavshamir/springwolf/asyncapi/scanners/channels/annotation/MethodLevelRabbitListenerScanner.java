package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.stavshamir.springwolf.SpringWolfAmqpConfigConstants.SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED;

@Slf4j
@Service
@Order(value = ChannelPriority.AUTO_DISCOVERED)
@ConditionalOnProperty(name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED, matchIfMissing = true)
public class MethodLevelRabbitListenerScanner extends AbstractMethodLevelListenerScanner<RabbitListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private final Map<String, Binding> bindingsMap;
    private StringValueResolver resolver;

    public MethodLevelRabbitListenerScanner(List<Binding> bindings) {
        bindingsMap = bindings.stream()
                .filter(Binding::isDestinationQueue)
                .collect(Collectors.toMap(Binding::getDestination, Function.identity()));
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<RabbitListener> getListenerAnnotationClass() {
        return RabbitListener.class;
    }

    protected String getChannelName(RabbitListener annotation) {
        if (annotation.queues().length > 0) {
            return getChannelNameFromQueues(annotation);
        }

        return getChannelNameFromBindings(annotation);
    }

    private String getChannelNameFromQueues(RabbitListener annotation) {
        return Arrays.stream(annotation.queues())
                .map(resolver::resolveStringValue)
                .peek(queue -> log.debug("Resolved queue name: {}", queue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue name was found in @RabbitListener annotation"));
    }

    private String getChannelNameFromBindings(RabbitListener annotation) {
        return Arrays.stream(annotation.bindings())
                .map(binding -> binding.value().name())
                .map(resolver::resolveStringValue)
                .peek(queue -> log.debug("Resolved queue name: {}", queue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No queue name was found in @RabbitListener annotation"));
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(RabbitListener annotation) {
        AMQPChannelBinding.ExchangeProperties exchangeProperties = new AMQPChannelBinding.ExchangeProperties();
        exchangeProperties.setName(getExchangeName(annotation));

        AMQPChannelBinding channelBinding = AMQPChannelBinding.builder()
                .is("routingKey")
                .exchange(exchangeProperties)
                .build();
        return Map.of("amqp", channelBinding);
    }

    private String getExchangeName(RabbitListener annotation) {
        String exchangeName = Stream.of(annotation.bindings())
                .map(binding -> binding.exchange().name())
                .findFirst()
                .orElse(null);
        if (exchangeName == null && bindingsMap.containsKey(getChannelName(annotation))) {
            Binding binding = bindingsMap.get(getChannelName(annotation));
            exchangeName = binding.getExchange();
        }
        if (exchangeName == null) {
            exchangeName = "";
        }

        return exchangeName;
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(RabbitListener annotation) {
        return Map.of("amqp", AMQPOperationBinding.builder().cc(getRoutingKeys(annotation)).build());
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(RabbitListener annotation) {
        // currently the feature to define amqp message binding is not implemented.
        return Map.of("amqp", new AMQPMessageBinding());
    }

    private List<String> getRoutingKeys(RabbitListener annotation) {
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
        if (routingKeys == null && bindingsMap.containsKey(getChannelName(annotation))) {
            Binding binding = bindingsMap.get(getChannelName(annotation));
            routingKeys = Collections.singletonList(binding.getRoutingKey());
        }

        String exchangeName = getExchangeName(annotation);
        if (exchangeName.isEmpty() && routingKeys == null) {
            routingKeys = Collections.singletonList(getChannelName(annotation));
        }

        return routingKeys;
    }

    protected Class<?> getPayloadType(Method method) {
        return SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
