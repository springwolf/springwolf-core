// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.components.header;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersBuilder;

public class AsyncHeadersForKafkaBuilder implements AsyncHeadersBuilder {
    @Override
    public SchemaObject buildHeaders(Class<?> payloadType) {
        return new AsyncHeadersForSpringKafkaBuilder("SpringKafkaDefaultHeaders-" + payloadType.getSimpleName())
                .withTypeIdHeader(payloadType.getTypeName())
                .build();
    }
}
