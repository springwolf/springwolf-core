// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;

import java.util.Map;

public class AsyncHeadersNotDocumented implements AsyncHeadersBuilder {
    /**
     * Per default, if no headers are explicitly defined, {@link AsyncHeadersNotUsed#NOT_USED} is used.
     * There can be headers, but don't have to be.
     */
    public static final SchemaObject NOT_DOCUMENTED = SchemaObject.builder()
            .type(SchemaType.OBJECT)
            .title("HeadersNotDocumented")
            .description("There can be headers, but they are not explicitly documented.")
            .properties(Map.of())
            .build();

    @Override
    public SchemaObject buildHeaders(PayloadSchemaObject payloadSchema) {
        return NOT_DOCUMENTED;
    }
}
