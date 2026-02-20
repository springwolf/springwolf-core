// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.common.inferrer;

import io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer.ChannelNameInferrer;
import io.github.springwolf.plugins.amqp.asyncapi.scanners.bindings.RabbitListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * {@link ChannelNameInferrer} that derives the channel name from a {@link RabbitListener} annotation
 * present on the same method.
 * <p>
 * The inference follows the same rules as the auto-discovery scanner:
 * {@code queues} → {@code queuesToDeclare[].name} → {@code bindings[].exchange.name} (with optional routing key).
 * <p>
 * If no {@link RabbitListener} annotation is present on the method, or the annotation does not
 * carry enough information to derive a name, {@link Optional#empty()} is returned so that the
 * next inferrer (if any) can be tried.
 */
@RequiredArgsConstructor
public class RabbitListenerChannelNameInferrer implements ChannelNameInferrer {

    private final StringValueResolver stringValueResolver;

    @Override
    public Optional<String> inferChannelName(Method method) {
        return Optional.ofNullable(method.getAnnotation(RabbitListener.class)).flatMap(annotation -> {
            try {
                return Optional.of(RabbitListenerUtil.getChannelName(annotation, stringValueResolver));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        });
    }
}
