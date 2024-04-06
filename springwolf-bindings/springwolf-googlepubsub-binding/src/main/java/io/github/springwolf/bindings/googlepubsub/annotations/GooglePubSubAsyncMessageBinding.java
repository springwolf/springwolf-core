// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @GooglePubSubAsyncMessageBinding} is a method-level annotation.
 * It configures the message binding for the Google pubsub protocol.
 * @see io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubMessageBinding
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface GooglePubSubAsyncMessageBinding {
    String type() default "googlepubsub";

    String orderingKey() default "";

    GooglePubSubAsyncMessageSchema schema() default @GooglePubSubAsyncMessageSchema;

    String bindingVersion() default "0.2.0";
}
