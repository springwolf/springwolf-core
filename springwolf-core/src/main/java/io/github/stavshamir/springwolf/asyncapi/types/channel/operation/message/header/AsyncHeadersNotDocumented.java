// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

public class AsyncHeadersNotDocumented implements AsyncHeadersBuilder {
    /**
     * Per default, if no headers are explicitly defined, NOT_DOCUMENTED is used.
     * There can be headers, but don't have to be.
     */
    public static final AsyncHeaders NOT_DOCUMENTED = new AsyncHeaders("HeadersNotDocumented");

    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return AsyncHeaders.NOT_DOCUMENTED;
    }
}
