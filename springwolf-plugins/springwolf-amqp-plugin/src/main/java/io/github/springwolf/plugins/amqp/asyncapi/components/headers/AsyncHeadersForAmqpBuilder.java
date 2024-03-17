// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;

public class AsyncHeadersForAmqpBuilder implements AsyncHeadersBuilder {

    private static final SchemaObject headers = new SchemaObject();

    static {
        headers.setTitle("SpringRabbitListenerDefaultHeaders");
    }

    @Override
    public SchemaObject buildHeaders(Class<?> payloadType) {
        return headers;
    }
}
