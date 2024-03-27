// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

public interface AsyncHeadersBuilder {
    AsyncHeaders buildHeaders(Class<?> payloadType);
}
