// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiService;

/**
 * Factory to create a Springwolf instance and {@link io.github.springwolf.asyncapi.v3.model.AsyncAPI}
 * for standalone usage without runtime Spring context.
 * <p>
 * Allows Springwolf usage at build time, for example in unit test.
 */
public interface StandaloneFactory {
    AsyncApiService getAsyncApiService();
}
