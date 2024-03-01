// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.asyncapi.components.headers;

import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;

public class AsyncHeadersForAmqpBuilder implements AsyncHeadersBuilder {

    private static final AsyncHeaders headers = new AsyncHeaders("SpringRabbitListenerDefaultHeaders");

    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return headers;
    }
}
