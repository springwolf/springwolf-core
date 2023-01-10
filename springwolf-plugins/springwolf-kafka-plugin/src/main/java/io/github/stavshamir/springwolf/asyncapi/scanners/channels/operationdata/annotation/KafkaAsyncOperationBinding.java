package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @KafkaAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or @{@link AsyncListener}.
 * It configures the operation binding for the Kafka protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
public @interface KafkaAsyncOperationBinding {

    String type() default "kafka";

    String groupId() default "";

    String clientId() default "";
    String bindingVersion() default "";

    KafkaAsyncMessageBinding messageBinding() default @KafkaAsyncMessageBinding();

    @Retention(RetentionPolicy.CLASS)
    @Target({})
    @interface KafkaAsyncMessageBinding {

        KafkaKeyTypes keyType() default KafkaKeyTypes.NO_KEY;

        String description() default "";

        String bindingVersion() default "";

        enum KafkaKeyTypes {
            NO_KEY,
            STRING_KEY
        }
    }
}
