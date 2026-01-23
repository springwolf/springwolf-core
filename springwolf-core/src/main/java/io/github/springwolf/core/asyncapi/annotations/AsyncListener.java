// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AsyncListener} is a method-level annotation to document springwolf listeners.
 * Listeners in this context are methods that receive and process incoming data from a message broker.
 * To document producers, use {@link AsyncPublisher}.
 * <p>
 * The fields channel, description, payload and headers are part of {@link AsyncOperation}.
 * If no {@link AsyncOperation#payloadType()} is passed, the payload type is extracted from the signature of the method.
 * Add {@link org.springframework.messaging.handler.annotation.Payload} to the payload argument, if the method has more than one argument.
 * <p>
 * Add an operation binding with one of the protocol specific operation binding annotation ({@code AmqpAsyncOperationBinding}, {@code KafkaAsyncOperationBinding}) on the same method.
 * <pre class="code">
 *     &#064;KafkaListener
 *     &#064;AsyncListener(operation = &#064;AsyncOperation(
 *             channelName = "topic-name",
 *             ...
 *     ))
 *     &#064;KafkaAsyncOperationBinding
 *     public void receiveMessage(MonetaryAmount payload) { ... }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Repeatable(AsyncListeners.class)
@Inherited
public @interface AsyncListener {
    AsyncOperation operation();
}
