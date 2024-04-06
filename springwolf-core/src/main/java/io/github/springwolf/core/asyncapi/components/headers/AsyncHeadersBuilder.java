// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

public interface AsyncHeadersBuilder {
    SchemaObject buildHeaders(Class<?> payloadType);
}
