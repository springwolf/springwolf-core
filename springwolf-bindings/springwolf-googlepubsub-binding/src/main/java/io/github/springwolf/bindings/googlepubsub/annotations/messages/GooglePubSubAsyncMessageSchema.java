// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations.messages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @GooglePubSubAsyncMessageSchema} is a method-level annotation.
 * It configures the message schema for the Google pubsub protocol.
 * @see io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchema
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface GooglePubSubAsyncMessageSchema {
    String name() default "";
}
