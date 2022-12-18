package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.types.OperationData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AsyncPublisher} is a method-level annotation to document springwolf publishers.
 * To document listeners, use {@link AsyncSubscriber}.
 * <p>
 * The fields channel, description, payload and headers are part of {@link AsyncOperation}
 * and behaves identical to {@link io.github.stavshamir.springwolf.asyncapi.types.ProducerData}.
 * If no {@link AsyncOperation#payloadType()} is passed, the payload type is extracted from the signature of the method.
 * Add {@link org.springframework.messaging.handler.annotation.Payload} to the payload argument, if the method has more than one argument.
 * <p>
 * Add an operation binding with one of the protocol specific operation binding annotation ({@code AmqpAsyncOperationBinding}, {@code KafkaAsyncOperationBinding}) on the same method.
 * <pre class="code">
 *     &#064;AsyncPublisher(operation = &#064;AsyncOperation(
 *             channelName = "topic-name",
 *             ...
 *     ))
 *     &#064;KafkaAsyncOperationBinding
 *     public void sendMessage(MonetaryAmount payload) { ... }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AsyncPublisher {
    /**
     * Mapped to {@link OperationData}
     */
    AsyncOperation operation();
}
