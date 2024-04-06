// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.bindings.googlepubsub.annotations;

import io.github.springwolf.asyncapi.v3.bindings.googlepubsub.GooglePubSubSchemaSettings;

import java.lang.annotation.Inherited;

/**
 * @see GooglePubSubSchemaSettings
 */
@Inherited
public @interface GooglePubSubAsyncSchemaSetting {
    /**
     * Mapped to {@link GooglePubSubSchemaSettings#getEncoding()}
     */
    String encoding() default "";

    /**
     * Mapped to {@link GooglePubSubSchemaSettings#getName()}
     */
    String name() default "";

    /**
     * Mapped to {@link GooglePubSubSchemaSettings#getFirstRevisionId()}
     */
    String firstRevisionId() default "";

    /**
     * Mapped to {@link GooglePubSubSchemaSettings#getLastRevisionId()}
     */
    String lastRevisionId() default "";
}
