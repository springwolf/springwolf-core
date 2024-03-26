// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations.channels;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @GooglePubSubAsyncChannelBinding} is a method-level annotation.
 * It configures the channel binding for the Google pubsub protocol.
 * @see io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubChannelBinding
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Inherited
public @interface GooglePubSubAsyncChannelBinding {
    String type() default "googlepubsub";

    String messageRetentionDuration() default "";

    GooglePubsubAsyncMessageStoragePolicy messageStoragePolicy() default @GooglePubsubAsyncMessageStoragePolicy;

    GooglePubSubAsyncSchemaSetting schemaSettings();

    String bindingVersion() default "0.2.0";
}
