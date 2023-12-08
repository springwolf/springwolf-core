// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

public class AsyncHeadersForAmqpBuilder implements AsyncHeadersBuilder {

    private static final AsyncHeaders headers = new AsyncHeaders("SpringRabbitListenerDefaultHeaders");

    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return headers;
    }
}
