// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.sns.annotations;

import io.github.springwolf.asyncapi.v3.bindings.sns.SNSOperationBindingIdentifier;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see SNSOperationBindingIdentifier
 * @deprecated Use {@link io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier} provided by {@code springwolf-sqs-binding}
 */
@Deprecated(since = "1.1.0", forRemoval = true)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
@Inherited
@io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier
public @interface SnsAsyncOperationBindingIdentifier {
    /**
     * Optional. The endpoint is a URL
     */
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier.class,
            attribute = "url")
    String url() default "";
    /**
     * Optional. The endpoint is an email address
     */
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier.class,
            attribute = "email")
    String email() default "";
    /**
     * Optional. The endpoint is a phone number
     */
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier.class,
            attribute = "phone")
    String phone() default "";
    /**
     * Optional. The target is an ARN. For example, for SQS, the identifier may be an ARN, which will be of the form:
     * "arn:aws:sqs:{region}:{account-id}:{queueName}"
     */
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier.class,
            attribute = "arn")
    String arn() default "";
    /**
     * Optional. The endpoint is identified by a name, which corresponds to an identifying field called 'name' of a
     * binding for that protocol on this publish Operation Object. For example, if the protocol is 'sqs' then the name
     * refers to the name field sqs binding. We don't use $ref because we are referring, not including.
     */
    @AliasFor(
            annotation = io.github.springwolf.bindings.sns.annotations.SnsAsyncOperationBindingIdentifier.class,
            attribute = "name")
    String name() default "";
}
