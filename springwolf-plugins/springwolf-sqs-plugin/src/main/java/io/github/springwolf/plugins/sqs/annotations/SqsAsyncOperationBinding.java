// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sqs.annotations;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @SqsAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncListener}.
 * It configures the operation binding for the SQS protocol.
 *
 * @deprecated Use {@link io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding} provided by {@code springwolf-sqs-binding}
 */
@Deprecated(since = "1.1.0", forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncOperationBinding
@io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding
@Inherited
public @interface SqsAsyncOperationBinding {

    String type() default "sqs";

    @SuppressWarnings({"deprecation", "removal"})
    @AliasFor(
            annotation = io.github.springwolf.bindings.sqs.annotations.SqsAsyncOperationBinding.class,
            attribute = "queues")
    SqsAsyncQueueBinding[] queues() default {};
}
