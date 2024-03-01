// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.components.header;

import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;

public class AsyncHeadersForKafkaBuilder implements AsyncHeadersBuilder {
    @Override
    public AsyncHeaders buildHeaders(Class<?> payloadType) {
        return new AsyncHeadersForSpringKafkaBuilder("SpringKafkaDefaultHeaders-" + payloadType.getSimpleName())
                .withTypeIdHeader(payloadType.getTypeName())
                .build();
    }
}
