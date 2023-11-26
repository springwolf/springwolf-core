// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

public interface AsyncHeadersBuilder {
    AsyncHeaders buildHeaders(Class<?> payloadType);
}
