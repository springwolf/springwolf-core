// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.common.inferrer;

import io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer.ChannelNameInferrer;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.KafkaListenerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * {@link ChannelNameInferrer} that derives the channel name from a {@link KafkaListener} annotation
 * present on the same method.
 * <p>
 * The inference follows the same rules as the auto-discovery scanner:
 * {@code topics} -> {@code topicPatterns}
 * <p>
 * If no {@link KafkaListener} annotation is present on the method, or the annotation does not
 * carry enough information to derive a name, {@link Optional#empty()} is returned so that the
 * next inferrer (if any) can be tried.
 */
@RequiredArgsConstructor
public class KafkaChannelNameInferrer implements ChannelNameInferrer {

    private final StringValueResolver stringValueResolver;

    @Override
    public Optional<String> inferChannelName(Method method) {
        var methodAnnotation = method.getAnnotation(KafkaListener.class);
        if (methodAnnotation != null) {
            return inferFromAnnotation(methodAnnotation);
        }

        if (method.isAnnotationPresent(KafkaHandler.class)) {
            var classAnnotation = method.getDeclaringClass().getAnnotation(KafkaListener.class);
            if (classAnnotation != null) {
                return inferFromAnnotation(classAnnotation);
            }
        }
        return Optional.empty();
    }

    private Optional<String> inferFromAnnotation(KafkaListener annotation) {
        try {
            return Optional.ofNullable(KafkaListenerUtil.getChannelName(annotation, stringValueResolver));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
