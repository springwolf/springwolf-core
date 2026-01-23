// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;

import java.util.Map;
import java.util.Set;

public class AsyncHeadersForAmqpBuilder implements AsyncHeadersBuilder {

    private static final SchemaObject headers = SchemaObject.builder()
            .type(Set.of(SchemaType.OBJECT.getValue()))
            .title("SpringRabbitListenerDefaultHeaders")
            .properties(Map.of())
            .build();

    @Override
    public SchemaObject buildHeaders(PayloadSchemaObject payloadSchema) {
        return headers;
    }
}
