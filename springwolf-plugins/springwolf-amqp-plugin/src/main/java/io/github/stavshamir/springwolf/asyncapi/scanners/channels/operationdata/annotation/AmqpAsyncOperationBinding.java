package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AmqpAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncSubscriber}.
 * It configures the operation binding for the AMQP protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface AmqpAsyncOperationBinding {

    String type() default "amqp";

    int expiration() default 0;

    String[] cc() default {};

    int priority() default 0;

    int deliveryMode() default 0;

    boolean mandatory() default false;

    boolean timestamp() default false;

    boolean ack() default false;
}
