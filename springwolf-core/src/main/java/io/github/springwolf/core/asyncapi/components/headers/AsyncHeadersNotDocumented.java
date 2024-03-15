// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

public class AsyncHeadersNotDocumented implements AsyncHeadersBuilder {
    /**
     * Per default, if no headers are explicitly defined, {@link AsyncHeaders#NOT_USED} is used.
     * There can be headers, but don't have to be.
     */
    public static final AsyncHeaders NOT_DOCUMENTED =
            new AsyncHeaders("HeadersNotDocumented", "There can be headers, but they are not explicitly documented.");

    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return NOT_DOCUMENTED;
    }
}
