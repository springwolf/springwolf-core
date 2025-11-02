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
 * <li> queuesToDeclare (object) will create queues on broker and matching beans (default exchange + routing key)
 * <li> queueBinding (object) will create queue and exchange on broker and matching beans (exchange must match if present)
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

    public static String getChannelName(RabbitListener annotation, StringValueResolver stringValueResolver) {
        Stream<String> annotationBindingChannelNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> channelNameFromAnnotationBindings(
                                binding.exchange().name(), binding.key())
                        .map(stringValueResolver::resolveStringValue));

        Stream<String> stream = Stream.concat(streamQueueNames(annotation), annotationBindingChannelNames);
        return resolveFirstValue(stream, stringValueResolver, "channel name");
    }

    public static String getChannelId(RabbitListener annotation, StringValueResolver stringValueResolver) {
        String channelName = getChannelName(annotation, stringValueResolver);
        return ReferenceUtil.toValidId(channelName);
    }

    private static String getQueueName(RabbitListener annotation, StringValueResolver resolver) {
        Stream<String> annotationBindingQueueNames = Arrays.stream(annotation.bindings())
                .flatMap(binding -> Stream.of(binding.value().name()));

        Stream<String> stream = Stream.concat(streamQueueNames(annotation), annotationBindingQueueNames);
        return resolveFirstValue(stream, resolver, "queue name");
    }

    private static Stream<String> channelNameFromAnnotationBindings(String exchangeName, String[] routingKeys) {
        List<String> keys = Arrays.stream(routingKeys)
                .filter(s -> !s.equals(DEFAULT_EXCHANGE_ROUTING_KEY))
                .toList();
        if (keys.isEmpty()) {
            return Stream.of(exchangeName);
        }
        return keys.stream().map(key -> String.join("_", exchangeName, key));
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
            RabbitListener annotation, StringValueResolver stringValueResolver, RabbitListenerUtilContext context) {
        AMQPChannelBinding.AMQPChannelBindingBuilder channelBinding = AMQPChannelBinding.builder();

        String exchangeName = getExchangeName(annotation, stringValueResolver, context);
        if (exchangeName.isEmpty()) {
            channelBinding.is(AMQPChannelType.QUEUE);
            channelBinding.queue(buildQueueProperties(annotation, stringValueResolver, context));
        } else {
            channelBinding.is(AMQPChannelType.ROUTING_KEY);
            channelBinding.exchange(buildExchangeProperties(annotation, exchangeName, context));
        }

        return Map.of(BINDING_NAME, channelBinding.build());
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
            RabbitListener annotation, StringValueResolver stringValueResolver, RabbitListenerUtilContext context) {
        String queueName = getQueueName(annotation, stringValueResolver);
        org.springframework.amqp.core.Queue queue = context.queueMap().get(queueName);
        boolean autoDelete = queue != null ? queue.isAutoDelete() : DEFAULT_AUTO_DELETE;
        boolean durable = queue != null ? queue.isDurable() : DEFAULT_DURABLE;
        boolean exclusive = queue != null ? queue.isExclusive() : DEFAULT_EXCLUSIVE;

        Optional<Queue> queueOpt =
                Arrays.stream(annotation.bindings()).map(QueueBinding::value).findFirst();
        if (queueOpt.isPresent()) {
            Queue queueAnnotation = queueOpt.get();
            return AMQPChannelQueueProperties.builder()
                    .name(stringValueResolver.resolveStringValue(queueAnnotation.name()))
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

    public static List<ChannelObject> buildChannelObject(Binding binding) {
        String exchangeName = channelNameFromAnnotationBindings(
                        binding.getExchange(), List.of(binding.getRoutingKey()).toArray(String[]::new))
                .findFirst()
                .get();
        return List.of(
                // exchange
                ChannelObject.builder()
                        .channelId(ReferenceUtil.toValidId(exchangeName))
                        .address(binding.getRoutingKey())
                        .bindings(Map.of(
                                BINDING_NAME,
                                AMQPChannelBinding.builder()
                                        .is(AMQPChannelType.ROUTING_KEY)
                                        .exchange(AMQPChannelExchangeProperties.builder()
                                                .name(binding.getExchange())
                                                .build())
                                        .build()))
                        .build(),
                // queue (where the exchange forwards the message to)
                ChannelObject.builder()
                        .channelId(ReferenceUtil.toValidId(binding.getDestination()))
                        .address(binding.getDestination())
                        .bindings(Map.of(
                                BINDING_NAME,
                                AMQPChannelBinding.builder()
                                        .is(AMQPChannelType.QUEUE)
                                        .queue(AMQPChannelQueueProperties.builder()
                                                .name(binding.getDestination())
                                                .build())
                                        .build()))
                        .build());
    }

    private static Boolean parse(String value, Boolean defaultIfEmpty) {
        if ("".equals(value)) {
            return defaultIfEmpty;
        }
        return Boolean.valueOf(value);
    }

    private static String getExchangeName(
            RabbitListener annotation, StringValueResolver stringValueResolver, RabbitListenerUtilContext context) {
        String exchangeName = Stream.of(annotation.bindings())
                .map(binding -> binding.exchange().name())
                .map(stringValueResolver::resolveStringValue)
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(null);

        Binding binding = context.bindingMap().get(getChannelName(annotation, stringValueResolver));
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
