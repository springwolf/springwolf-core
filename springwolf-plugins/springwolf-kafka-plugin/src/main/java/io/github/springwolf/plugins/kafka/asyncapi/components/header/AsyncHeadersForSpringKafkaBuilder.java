// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.components.header;

import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaderSchema;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import org.springframework.kafka.support.mapping.AbstractJavaTypeMapper;

import java.util.List;

public class AsyncHeadersForSpringKafkaBuilder {
    private final AsyncHeaders headers;

    public AsyncHeadersForSpringKafkaBuilder() {
        this("SpringKafkaDefaultHeaders");
    }

    public AsyncHeadersForSpringKafkaBuilder(String schemaName) {
        this.headers = new AsyncHeaders(schemaName);
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId) {
        return withTypeIdHeader(exampleTypeId, List.of(exampleTypeId));
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId, List<String> types) {
        return this.withHeader(
                AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, types, exampleTypeId, "Spring Type Id Header");
    }

    private AsyncHeadersForSpringKafkaBuilder withHeader(
            String headerName, List<String> types, String exampleType, String description) {
        AsyncHeaderSchema header = AsyncHeaderSchema.headerBuilder()
                .headerName(headerName)
                .description(description)
                .example(exampleType)
                .enumValue(types)
                .build();
        headers.addHeader(header);
        return this;
    }

    public AsyncHeaders build() {
        return AsyncHeaders.from(this.headers, this.headers.getSchemaName());
    }
}
