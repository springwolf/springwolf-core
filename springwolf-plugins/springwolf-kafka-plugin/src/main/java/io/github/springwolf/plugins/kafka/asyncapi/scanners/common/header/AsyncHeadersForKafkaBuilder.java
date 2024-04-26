// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.common.header;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.plugins.kafka.asyncapi.components.header.AsyncHeadersForSpringKafkaBuilder;

public class AsyncHeadersForKafkaBuilder implements AsyncHeadersBuilder {
    @Override
    public SchemaObject buildHeaders(NamedSchemaObject payloadSchema) {
        return new AsyncHeadersForSpringKafkaBuilder(
                        "SpringKafkaDefaultHeaders-" + payloadSchema.schema().getTitle())
                .withTypeIdHeader(payloadSchema.name())
                .build();
    }
}
