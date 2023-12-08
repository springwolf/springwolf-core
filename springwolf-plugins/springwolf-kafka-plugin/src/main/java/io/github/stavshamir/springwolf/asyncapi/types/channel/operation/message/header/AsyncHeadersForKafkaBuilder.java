// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

public class AsyncHeadersForKafkaBuilder implements AsyncHeadersBuilder {
    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return new AsyncHeadersForSpringKafkaBuilder("SpringKafkaDefaultHeaders-" + payloadType.getSimpleName())
                .withTypeIdHeader(payloadType.getTypeName())
                .build();
    }
}
