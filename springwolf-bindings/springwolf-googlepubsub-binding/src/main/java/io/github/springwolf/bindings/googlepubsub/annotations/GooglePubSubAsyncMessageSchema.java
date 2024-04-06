// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations;

import java.lang.annotation.Inherited;

/**
 * {@code @GooglePubSubAsyncMessageSchema} is a method-level annotation.
 * It configures the message schema for the Google pubsub protocol.
 * @see io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchema
 */
@Inherited
public @interface GooglePubSubAsyncMessageSchema {
    String name() default "";
}
