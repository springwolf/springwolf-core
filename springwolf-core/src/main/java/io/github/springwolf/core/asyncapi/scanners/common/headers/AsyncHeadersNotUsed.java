// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;

import java.util.Map;
import java.util.Set;

public class AsyncHeadersNotUsed implements AsyncHeadersBuilder {
    /**
     * Explicitly document that no headers are used.
     */
    public static final SchemaObject NOT_USED = SchemaObject.builder()
            .type(Set.of(SchemaType.OBJECT))
            .title("HeadersNotUsed")
            .description("No headers are present.")
            .properties(Map.of())
            .build();

    @Override
    public SchemaObject buildHeaders(PayloadSchemaObject payloadSchema) {
        return NOT_USED;
    }
}
