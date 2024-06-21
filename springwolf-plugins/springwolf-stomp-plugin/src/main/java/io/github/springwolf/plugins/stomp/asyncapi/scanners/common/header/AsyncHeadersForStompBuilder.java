// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.stomp.asyncapi.scanners.common.header;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;

import java.util.Map;

public class AsyncHeadersForStompBuilder implements AsyncHeadersBuilder {

    private static final SchemaObject headers = SchemaObject.builder()
            .type("object")
            .title("SpringStompDefaultHeaders")
            .properties(Map.of())
            .build();

    @Override
    public SchemaObject buildHeaders(NamedSchemaObject payloadSchema) {
        return headers;
    }
}
