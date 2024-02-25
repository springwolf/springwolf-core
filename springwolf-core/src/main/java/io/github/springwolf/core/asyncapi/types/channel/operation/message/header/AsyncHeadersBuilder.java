// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.types.channel.operation.message.header;

public interface AsyncHeadersBuilder {
    AsyncHeaders buildHeaders(Class<?> payloadType);
}
