// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

public class AsyncHeadersNotUsed implements AsyncHeadersBuilder {
    /**
     * Explicitly document that no headers are used.
     */
    public static final AsyncHeaders NOT_USED = new AsyncHeaders("HeadersNotUsed", "No headers are present.");

    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return NOT_USED;
    }
}
