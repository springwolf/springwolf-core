// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;

public interface AsyncHeadersBuilder {
    SchemaObject buildHeaders(NamedSchemaObject payloadSchema);
}
