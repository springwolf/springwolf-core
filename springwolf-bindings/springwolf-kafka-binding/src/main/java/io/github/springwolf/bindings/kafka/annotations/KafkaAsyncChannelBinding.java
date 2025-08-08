// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.kafka.annotations;

import io.github.springwolf.core.asyncapi.annotations.AsyncChannelBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @KafkaAsyncChannelBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or @{@link AsyncListener}.
 * It configures the channel binding for the Kafka protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncChannelBinding
@Inherited
public @interface KafkaAsyncChannelBinding {

    String topic() default "";

    int partitions() default VALUE_NOT_SET;

    int replicas() default VALUE_NOT_SET;

    KafkaChannelTopicConfiguration topicConfiguration() default @KafkaChannelTopicConfiguration();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @interface KafkaChannelTopicConfiguration {

        CleanupPolicy[] cleanup() default {};

        long retentionMs() default VALUE_NOT_SET;

        long retentionBytes() default VALUE_NOT_SET;

        long deleteRetentionMs() default VALUE_NOT_SET;

        int maxMessageBytes() default VALUE_NOT_SET;

        enum CleanupPolicy {
            COMPACT,
            DELETE,
        }
    }

    int VALUE_NOT_SET = Integer.MIN_VALUE;
}
