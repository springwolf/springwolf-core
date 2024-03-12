// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @deprecated Use {@link io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding} provided by {@code springwolf-sqs-binding}
 */
@Deprecated(since = "1.1.0", forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding
@Inherited
public @interface SqsAsyncQueueBinding {

    @AliasFor(annotation = io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding.class, attribute = "name")
    String name() default "";

    @AliasFor(
            annotation = io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding.class,
            attribute = "fifoQueue")
    boolean fifoQueue() default true;

    @AliasFor(
            annotation = io.github.springwolf.bindings.sqs.annotations.SqsAsyncQueueBinding.class,
            attribute = "deliveryDelay")
    int deliveryDelay() default 0;
}
