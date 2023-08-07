package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.channel.amqp.AMQPChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.message.amqp.AMQPMessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import com.asyncapi.v2.binding.operation.amqp.AMQPOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class RabbitListenerUtil {
    private static final Boolean DEFAULT_AUTO_DELETE = false;
    private static final Boolean DEFAULT_DURABLE = true;
    private static final Boolean DEFAULT_EXCLUSIVE = false;
    private static final String DEFAULT_EXCHANGE_TYPE = ExchangeTypes.DIRECT;

    public static String getChannelName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationQueueNames = Arrays.stream(annotation.queues());
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.concat(
                        Stream.of(binding.key()), // if routing key is configured, prefer it
                        Stream.of(binding.value().name())));

        return Stream.concat(annotationQueueNames, annotationBindingChannelNames)
                .map(resolver::resolveStringValue)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No channel name was found in @RabbitListener annotation (neither in queues nor bindings property)"));
    }

    public static String getQueueName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationQueueNames = Arrays.stream(annotation.queues());
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.of(binding.value().name()));

        return Stream.concat(annotationQueueNames, annotationBindingChannelNames)
                .map(resolver::resolveStringValue)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No queue name was found in @RabbitListener annotation (neither in queues nor bindings property)"));
    }

    public static Map<String, ? extends ChannelBinding> buildChannelBinding(
            RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        AMQPChannelBinding.AMQPChannelBindingBuilder channelBinding = AMQPChannelBinding.builder();
        channelBinding.queue(buildQueueProperties(annotation, resolver));

        String exchangeName = getExchangeName(annotation, resolver, bindingsMap);
        channelBinding.exchange(buildExchangeProperties(annotation, exchangeName));
        if (exchangeName.isEmpty()) {
            channelBinding.is("queue");
        } else {
            channelBinding.is("routingKey");
        }

        return Map.of("amqp", channelBinding.build());
    }

    private static AMQPChannelBinding.ExchangeProperties buildExchangeProperties(
            RabbitListener annotation, String exchangeName) {
        return AMQPChannelBinding.ExchangeProperties.builder()
                .name(exchangeName)
                .type(Stream.of(annotation.bindings())
                        .map(it -> it.exchange().type())
                        .findFirst()
                        .orElse(DEFAULT_EXCHANGE_TYPE))
                .durable(Boolean.valueOf(Stream.of(annotation.bindings())
                        .map(it -> it.exchange().durable())
                        .findFirst()
                        .orElse(DEFAULT_DURABLE.toString())))
                .autoDelete(Boolean.valueOf(Stream.of(annotation.bindings())
                        .map(it -> it.exchange().autoDelete())
                        .findFirst()
                        .orElse(DEFAULT_AUTO_DELETE.toString())))
                .build();
    }

    private static AMQPChannelBinding.QueueProperties buildQueueProperties(
            RabbitListener annotation, StringValueResolver resolver) {
        Optional<Queue> queueOpt =
                Arrays.stream(annotation.bindings()).map(QueueBinding::value).findFirst();
        if (queueOpt.isPresent()) {
            Queue queue = queueOpt.get();
            return AMQPChannelBinding.QueueProperties.builder()
                    .name(resolver.resolveStringValue(queue.name()))
                    .autoDelete(parse(queue.autoDelete(), DEFAULT_AUTO_DELETE))
                    .durable(parse(queue.durable(), DEFAULT_DURABLE))
                    .exclusive(parse(queue.exclusive(), DEFAULT_EXCLUSIVE))
                    .build();
        }
        return AMQPChannelBinding.QueueProperties.builder()
                .name(getQueueName(annotation, resolver))
                .autoDelete(DEFAULT_AUTO_DELETE)
                .durable(DEFAULT_DURABLE)
                .exclusive(DEFAULT_EXCLUSIVE)
                .build();
    }

    private static Boolean parse(String value, Boolean defaultIfEmpty) {
        if ("".equals(value)) {
            return defaultIfEmpty;
        }
        return Boolean.valueOf(value);
    }

    private static String getExchangeName(
            RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        String exchangeName = Stream.of(annotation.bindings())
                .map(binding -> binding.exchange().name())
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);

        // check Binding beans
        if (exchangeName == null && bindingsMap.containsKey(getChannelName(annotation, resolver))) {
            Binding binding = bindingsMap.get(getChannelName(annotation, resolver));
            exchangeName = binding.getExchange();
        }

        if (exchangeName == null) {
            // The amqp default exchange is represented with an empty string
            exchangeName = "";
        }

        return exchangeName;
    }

    public static Map<String, ? extends OperationBinding> buildOperationBinding(
            RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        return Map.of(
                "amqp",
                AMQPOperationBinding.builder()
                        .cc(getRoutingKeys(annotation, resolver, bindingsMap))
                        .build());
    }

    private static List<String> getRoutingKeys(
            RabbitListener annotation, StringValueResolver resolver, Map<String, Binding> bindingsMap) {
        List<String> routingKeys = Stream.of(annotation.bindings())
                .map(binding -> {
                    if (binding.key().length == 0) {
                        // The routing key is taken from the binding. As the key field in the @QueueBinding can be an
                        // empty array, it is set as an empty String in that case.
                        return Collections.singletonList("");
                    }

                    return Arrays.stream(binding.key())
                            .map(resolver::resolveStringValue)
                            .toList();
                })
                .findFirst()
                .orElse(null);

        // check Binding beans
        if (routingKeys == null && bindingsMap.containsKey(getChannelName(annotation, resolver))) {
            Binding binding = bindingsMap.get(getChannelName(annotation, resolver));
            routingKeys = Collections.singletonList(binding.getRoutingKey());
        }

        // when there is no binding for the queue present at all, it uses the fact that
        // RabbitMQ automatically binds default exchange to a queue with queue's name as a routing key.
        String exchangeName = getExchangeName(annotation, resolver, bindingsMap);
        if (exchangeName.isEmpty() && routingKeys == null) {
            routingKeys = Collections.singletonList(getQueueName(annotation, resolver));
        }

        return routingKeys;
    }

    public static Map<String, ? extends MessageBinding> buildMessageBinding() {
        // currently the feature to define amqp message binding is not implemented.
        return Map.of("amqp", new AMQPMessageBinding());
    }
}
