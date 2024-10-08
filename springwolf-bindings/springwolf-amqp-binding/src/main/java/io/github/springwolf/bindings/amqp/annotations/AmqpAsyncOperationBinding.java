// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.amqp.annotations;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @AmqpAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncListener}.
 * It configures the operation binding for the AMQP protocol.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncOperationBinding
@Inherited
public @interface AmqpAsyncOperationBinding {

    int expiration() default 0;

    String userId() default "";

    String[] cc() default {};

    int priority() default 0;

    int deliveryMode() default 1;

    boolean mandatory() default false;

    String[] bcc() default {};

    boolean timestamp() default false;

    boolean ack() default false;
}
