// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.types.channel.operation.message.header;

import io.github.springwolf.core.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.springwolf.core.asyncapi.types.channel.operation.message.header.AsyncHeadersBuilder;

public class AsyncHeadersForKafkaBuilder implements AsyncHeadersBuilder {
    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return new AsyncHeadersForSpringKafkaBuilder("SpringKafkaDefaultHeaders-" + payloadType.getSimpleName())
                .withTypeIdHeader(payloadType.getTypeName())
                .build();
    }
}
