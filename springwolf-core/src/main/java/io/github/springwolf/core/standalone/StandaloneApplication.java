// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.asyncapi.AsyncApiService;

/**
 * Standalone application instance to access {@link io.github.springwolf.asyncapi.v3.model.AsyncAPI}
 * without runtime Spring context.
 * <p>
 * Allows Springwolf usage at build time, for example in unit test.
 */
public interface StandaloneApplication {
    AsyncApiService getAsyncApiService();
}
