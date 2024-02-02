// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.AsyncAPI;

/**
 * Allows to modify the final AsyncApi document after it has been generated.
 * <br/>
 * There is no check that the document is still AsyncApi conform after any applied modification.
 * <br/>
 * This is a low level API. The AsyncApi class may change during versions and break your customizers.
 * <br/>
 * When you feel the need to use this, we like to hear from you.
 * Ideally we upstream your changes into the main project,
 * so that your customizer does not break and other people can benefit as well.
 */
public interface AsyncApiCustomizer {
    /**
     * Customize the AsyncApi document in-place using its setter methods.
     *
     * @param asyncAPI The Springwolf AsyncApi class
     */
    void customize(AsyncAPI asyncAPI);
}
