// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.scanners.channels.operationdata.annotation;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @KafkaAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or @{@link AsyncListener}.
 * It configures the operation binding for the Kafka protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncOperationBinding
@Inherited
public @interface KafkaAsyncOperationBinding {

    String type() default "kafka";

    String groupId() default "";

    String clientId() default "";

    String bindingVersion() default "";

    KafkaAsyncMessageBinding messageBinding() default @KafkaAsyncMessageBinding();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @interface KafkaAsyncMessageBinding {

        KafkaAsyncKey key() default @KafkaAsyncKey(type = KafkaAsyncKey.KafkaKeyTypes.UNDEFINED_KEY);

        String bindingVersion() default "";
    }

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @interface KafkaAsyncKey {

        KafkaKeyTypes type() default KafkaKeyTypes.STRING_KEY;

        String example() default "";

        String description() default "";

        enum KafkaKeyTypes {
            UNDEFINED_KEY,
            STRING_KEY
        }
    }
}
