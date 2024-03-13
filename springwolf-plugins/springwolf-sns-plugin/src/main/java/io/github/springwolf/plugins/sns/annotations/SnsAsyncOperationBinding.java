// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.annotations;

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
 * {@code @SnsAsyncOperationBinding} is a method-level annotation used in combination with {@link AsyncPublisher} or {@link AsyncListener}.
 * It configures the operation binding for the SNS protocol.
 *
 * @deprecated Use {@link io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding} provided by {@code springwolf-sqs-binding}
 */
@Deprecated(since = "1.1.0", forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@AsyncOperationBinding
// FIXME
@io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding(
        protocol = "",
        endpoint = @io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier())
@Inherited
public @interface SnsAsyncOperationBinding {

    String type() default "sns";

    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding.class,
            attribute = "protocol")
    String protocol();

    @SuppressWarnings({"deprecation", "removal"})
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding.class,
            attribute = "endpoint")
    SnsAsyncOperationBindingIdentifier endpoint();

    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBinding.class,
            attribute = "rawMessageDelivery")
    boolean rawMessageDelivery() default true;
}
