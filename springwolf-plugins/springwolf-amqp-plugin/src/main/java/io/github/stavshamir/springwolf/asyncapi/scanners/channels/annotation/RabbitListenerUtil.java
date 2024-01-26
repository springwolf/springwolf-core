// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeType;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelQueueProperties;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelType;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
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
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class RabbitListenerUtil {
    private static final Boolean DEFAULT_AUTO_DELETE = false;
    private static final Boolean DEFAULT_DURABLE = true;
    private static final Boolean DEFAULT_EXCLUSIVE = false;
    private static final String DEFAULT_EXCHANGE_TYPE = ExchangeTypes.DIRECT;

    public static String getChannelName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.concat(
                        Stream.of(binding.key()), // if routing key is configured, prefer it
                        Stream.of(binding.value().name())));

        return Stream.concat(streamQueueNames(annotation), annotationBindingChannelNames)
                .map(resolver::resolveStringValue)
                .filter(Objects::nonNull)
                .peek(queue -> log.debug("Resolved channel name: {}", queue))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No channel name was found in @RabbitListener annotation (neither in queues nor bindings property)"));
    }

    public static String getQueueName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.of(binding.value().name()));

        return Stream.concat(streamQueueNames(annotation), annotationBindingChannelNames)
                .map(resolver::resolveStringValue)
                .filter(Objects::nonNull)
                .peek(queue -> log.debug("Resolved queue name: {}", queue))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "No queue name was found in @RabbitListener annotation (neither in queues nor bindings property)"));
    }

    /**
     *
     * @param rabbitListenerAnnotation a RabbitListener annotation
     * @return A stream of ALL queue names as defined in the following 'locations':
     * <UL>
     *  <LI>{@link RabbitListener#queues()}</LI>
     *  <LI>{@link RabbitListener#queuesToDeclare()}.name</LI>
     * </UL>
     * Note: queues, queuesToDeclare (and bindings) are mutually exclusive
     * @see <a href="https://docs.spring.io/spring-amqp/api/org/springframework/amqp/rabbit/annotation/RabbitListener.html#queuesToDeclare()">RabbitListener.queuesToDeclare</a>
     * */
    private static Stream<String> streamQueueNames(RabbitListener rabbitListenerAnnotation) {
        return Stream.concat(
                Arrays.stream(rabbitListenerAnnotation.queues()),
                Arrays.stream(rabbitListenerAnnotation.queuesToDeclare()).map(Queue::name));
    }

    public static Map<String, ChannelBinding> buildChannelBinding(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
        AMQPChannelBinding.AMQPChannelBindingBuilder channelBinding = AMQPChannelBinding.builder();

        String exchangeName = getExchangeName(annotation, resolver, context);
        if (exchangeName.isEmpty()) {
            channelBinding.is(AMQPChannelType.QUEUE);
            channelBinding.queue(buildQueueProperties(annotation, resolver, context));
        } else {
            channelBinding.is(AMQPChannelType.ROUTING_KEY);
            channelBinding.exchange(buildExchangeProperties(annotation, exchangeName, context));
        }

        return Map.of("amqp", channelBinding.build());
    }

    private static AMQPChannelExchangeProperties buildExchangeProperties(
            RabbitListener annotation, String exchangeName, RabbitListenerUtilContext context) {

        // When a bean is found, its values are preferred regardless of the annotations values.
        // When using the annotation, it is not possible to differentiate between user set and default parameters
        Exchange exchange = context.exchangeMap.get(exchangeName);
        if (exchange != null) {
            return AMQPChannelExchangeProperties.builder()
                    .name(exchangeName)
                    .type(AMQPChannelExchangeType.fromString(exchange.getType()))
                    .durable(exchange.isDurable())
                    .autoDelete(exchange.isAutoDelete())
                    .build();
        }

        var type = Stream.of(annotation.bindings())
                .map(it -> it.exchange().type())
                .findFirst()
                .orElse(DEFAULT_EXCHANGE_TYPE);

        return AMQPChannelExchangeProperties.builder()
                .name(exchangeName)
                .type(AMQPChannelExchangeType.fromString(type))
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

    private static AMQPChannelQueueProperties buildQueueProperties(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
        String queueName = getQueueName(annotation, resolver);
        org.springframework.amqp.core.Queue queue = context.queueMap.get(queueName);
        boolean autoDelete = queue != null ? queue.isAutoDelete() : DEFAULT_AUTO_DELETE;
        boolean durable = queue != null ? queue.isDurable() : DEFAULT_DURABLE;
        boolean exclusive = queue != null ? queue.isExclusive() : DEFAULT_EXCLUSIVE;

        Optional<Queue> queueOpt =
                Arrays.stream(annotation.bindings()).map(QueueBinding::value).findFirst();
        if (queueOpt.isPresent()) {
            Queue queueAnnotation = queueOpt.get();
            return AMQPChannelQueueProperties.builder()
                    .name(resolver.resolveStringValue(queueAnnotation.name()))
                    .autoDelete(parse(queueAnnotation.autoDelete(), autoDelete))
                    .durable(parse(queueAnnotation.durable(), durable))
                    .exclusive(parse(queueAnnotation.exclusive(), exclusive))
                    .build();
        }

        return AMQPChannelQueueProperties.builder()
                .name(queueName)
                .autoDelete(autoDelete)
                .durable(durable)
                .exclusive(exclusive)
                .build();
    }

    private static Boolean parse(String value, Boolean defaultIfEmpty) {
        if ("".equals(value)) {
            return defaultIfEmpty;
        }
        return Boolean.valueOf(value);
    }

    private static String getExchangeName(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
        String exchangeName = Stream.of(annotation.bindings())
                .map(binding -> binding.exchange().name())
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);

        Binding binding = context.bindingMap.get(getChannelName(annotation, resolver));
        if (exchangeName == null && binding != null) {
            exchangeName = binding.getExchange();
        }

        if (exchangeName == null) {
            // The amqp default exchange is represented with an empty string
            exchangeName = "";
        }

        return exchangeName;
    }

    public static Map<String, OperationBinding> buildOperationBinding(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
        return Map.of(
                "amqp",
                AMQPOperationBinding.builder()
                        .cc(getRoutingKeys(annotation, resolver, context))
                        .build());
    }

    private static List<String> getRoutingKeys(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
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

        Binding binding = context.bindingMap.get(getChannelName(annotation, resolver));
        if (routingKeys == null && binding != null) {
            routingKeys = Collections.singletonList(binding.getRoutingKey());
        }

        // when there is no binding for the queue present at all, it uses the fact that
        // RabbitMQ automatically binds default exchange to a queue with queue's name as a routing key.
        String exchangeName = getExchangeName(annotation, resolver, context);
        if (routingKeys == null && exchangeName.isEmpty()) {
            routingKeys = Collections.singletonList(getQueueName(annotation, resolver));
        }

        return routingKeys;
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        // currently the feature to define amqp message binding is not implemented.
        return Map.of("amqp", new AMQPMessageBinding());
    }

    public record RabbitListenerUtilContext(
            Map<String, org.springframework.amqp.core.Queue> queueMap,
            Map<String, Exchange> exchangeMap,
            Map<String, Binding> bindingMap) {

        public static RabbitListenerUtilContext create(
                List<org.springframework.amqp.core.Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
            Map<String, org.springframework.amqp.core.Queue> queueMap = queues.stream()
                    .collect(Collectors.toMap(
                            org.springframework.amqp.core.Queue::getName, Function.identity(), (e1, e2) -> e1));
            Map<String, Exchange> exchangeMap = exchanges.stream()
                    .collect(Collectors.toMap(Exchange::getName, Function.identity(), (e1, e2) -> e1));
            Map<String, Binding> bindingMap = bindings.stream()
                    .filter(Binding::isDestinationQueue)
                    .collect(Collectors.toMap(Binding::getDestination, Function.identity(), (e1, e2) -> e1));
            return new RabbitListenerUtil.RabbitListenerUtilContext(queueMap, exchangeMap, bindingMap);
        }
    }
}
