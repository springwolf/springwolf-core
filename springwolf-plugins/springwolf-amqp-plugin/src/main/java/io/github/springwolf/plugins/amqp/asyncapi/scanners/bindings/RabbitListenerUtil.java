// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelExchangeType;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelQueueProperties;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPChannelType;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPMessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.amqp.AMQPOperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Note: bindings, queues, and queuesToDeclare are mutually exclusive
 * <ul>
 * <li> queues (string) point to queue beans (default exchange + routing key)
 * <li> queuesToDeclare (object) will create queues on broker & matching beans (default exchange + routing key)
 * <li> queueBinding (object) will create queue and exchange on broker & matching beans (exchange must match if present)
 * </ul>
 * <br/>
 * How does rabbitmq work?
 * <ol>
 * <li> Producer sends a message to an exchange (default exchange if not specified)
 * <li> Exchange routes the message to a queue based on the routing key (routing key = queue name if not specified)
 * <li> Consumer consumes the message from the queue
 * </ol>
 */
@Slf4j
public class RabbitListenerUtil {
    public static final String BINDING_NAME = "amqp";
    private static final Boolean DEFAULT_AUTO_DELETE = false;
    private static final Boolean DEFAULT_DURABLE = true;
    private static final Boolean DEFAULT_EXCLUSIVE = false;
    private static final String DEFAULT_EXCHANGE_TYPE = ExchangeTypes.DIRECT;
    private static final String DEFAULT_EXCHANGE_ROUTING_KEY = "#";

    public static String getChannelName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> channelNameFromAnnotationBindings(binding, resolver));

        Stream<String> stream = Stream.concat(streamQueueNames(annotation), annotationBindingChannelNames);
        return resolveFirstValue(stream, resolver, "channel name");
    }

    public static String getChannelId(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingChannelIds = Arrays.stream(annotation.bindings())
                .flatMap(binding -> channelIdFromAnnotationBindings(binding, resolver));

        Stream<String> stream =
                Stream.concat(streamQueueNames(annotation).map(ReferenceUtil::toValidId), annotationBindingChannelIds);
        return resolveFirstValue(stream, resolver, "channel id");
    }

    private static String getQueueName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingQueueNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.of(binding.value().name()));

        Stream<String> stream = Stream.concat(streamQueueNames(annotation), annotationBindingQueueNames);
        return resolveFirstValue(stream, resolver, "queue name");
    }

    private static Stream<String> channelNameFromAnnotationBindings(
            QueueBinding binding, StringValueResolver resolver) {
        String exchangeName = resolver.resolveStringValue(binding.exchange().name());

        String[] routingKeys = binding.key();
        if (routingKeys.length == 0) {
            routingKeys = List.of(DEFAULT_EXCHANGE_ROUTING_KEY).toArray(new String[0]);
        }

        return Arrays.stream(routingKeys).map(resolver::resolveStringValue).map(routingKey -> exchangeName);
    }

    private static Stream<String> channelIdFromAnnotationBindings(QueueBinding binding, StringValueResolver resolver) {
        String queueName = resolver.resolveStringValue(binding.value().name());
        String exchangeName = resolver.resolveStringValue(binding.exchange().name());

        String[] routingKeys = binding.key();
        if (routingKeys.length == 0) {
            routingKeys = List.of(DEFAULT_EXCHANGE_ROUTING_KEY).toArray(new String[0]);
        }

        return Arrays.stream(routingKeys)
                .map(resolver::resolveStringValue)
                .map(routingKey -> String.join("_", queueName + "-id", routingKey, exchangeName + "-id"));
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
            channelBinding.name(getRoutingPattern(annotation, resolver, context));
            channelBinding.channel(
                    ChannelReference.fromChannel(exchangeTargetChannelIdFromAnnotationBindings(annotation, resolver)));
            channelBinding.exchange(buildExchangeProperties(annotation, exchangeName, context));
        }

        return Map.of(BINDING_NAME, channelBinding.build());
    }

    private static String exchangeTargetChannelIdFromAnnotationBindings(
            RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> stream = Arrays.stream(annotation.bindings())
                .map(binding -> binding.value().name() + "-id");

        return resolveFirstValue(stream, resolver, "exchange target channel id");
    }

    private static AMQPChannelExchangeProperties buildExchangeProperties(
            RabbitListener annotation, String exchangeName, RabbitListenerUtilContext context) {

        // When a bean is found, its values are preferred regardless of the annotations values.
        // When using the annotation, it is not possible to differentiate between user set and default parameters
        Exchange exchange = context.exchangeMap().get(exchangeName);
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
        org.springframework.amqp.core.Queue queue = context.queueMap().get(queueName);
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

    public static ChannelObject buildChannelObject(org.springframework.amqp.core.Queue queue) {
        return ChannelObject.builder()
                .channelId(ReferenceUtil.toValidId(queue.getName()))
                .address(queue.getName())
                .bindings(Map.of(
                        BINDING_NAME,
                        AMQPChannelBinding.builder()
                                .is(AMQPChannelType.QUEUE)
                                .queue(AMQPChannelQueueProperties.builder()
                                        .name(queue.getName())
                                        .autoDelete(queue.isAutoDelete())
                                        .durable(queue.isDurable())
                                        .exclusive(queue.isExclusive())
                                        .build())
                                .build()))
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
                .map(resolver::resolveStringValue)
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);

        Binding binding = context.bindingMap().get(getChannelName(annotation, resolver));
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
        return Map.of(BINDING_NAME, AMQPOperationBinding.builder().build());
    }

    private static String getRoutingPattern(
            RabbitListener annotation, StringValueResolver resolver, RabbitListenerUtilContext context) {
        String routingKey = Stream.of(annotation.bindings())
                .flatMap(binding -> Arrays.stream(binding.key()).map(resolver::resolveStringValue))
                .findFirst()
                .orElse(null);

        Binding binding = context.bindingMap().get(getChannelName(annotation, resolver));
        if (routingKey == null && binding != null) {
            routingKey = binding.getRoutingKey();
        }

        // when there is no binding for the queue present at all, it uses the fact that
        // RabbitMQ automatically binds default exchange to a queue with queue's name as a routing key.
        String exchangeName = getExchangeName(annotation, resolver, context);
        if (routingKey == null && exchangeName.isEmpty()) {
            routingKey = getQueueName(annotation, resolver);
        }

        if (routingKey == null) {
            routingKey = DEFAULT_EXCHANGE_ROUTING_KEY;
        }

        return routingKey;
    }

    public static Map<String, MessageBinding> buildMessageBinding() {
        // currently the feature to define amqp message binding is not implemented.
        return Map.of(BINDING_NAME, new AMQPMessageBinding());
    }

    private static String resolveFirstValue(Stream<String> values, StringValueResolver resolver, String valueType) {
        return values.map(resolver::resolveStringValue)
                .filter(Objects::nonNull)
                .peek(value -> log.debug("Resolved {}: {}", valueType, value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No " + valueType
                        + " was found in @RabbitListener annotation (neither in queues nor bindings property)"));
    }
}
