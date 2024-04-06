// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;

import java.util.Map;

public class AsyncHeadersForAmqpBuilder implements AsyncHeadersBuilder {

    private static final SchemaObject headers = new SchemaObject();

    static {
        headers.setType("object");
        headers.setTitle("SpringRabbitListenerDefaultHeaders");
        headers.setProperties(Map.of());
    }

    @Override
    public SchemaObject buildHeaders(Class<?> payloadType) {
        return headers;
    }
}
