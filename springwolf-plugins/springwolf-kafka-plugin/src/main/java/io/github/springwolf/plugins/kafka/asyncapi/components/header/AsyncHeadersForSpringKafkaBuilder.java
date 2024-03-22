// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.components.header;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import org.springframework.kafka.support.mapping.AbstractJavaTypeMapper;

import java.util.HashMap;
import java.util.List;

public class AsyncHeadersForSpringKafkaBuilder {
    private final SchemaObject headers;

    public AsyncHeadersForSpringKafkaBuilder() {
        this("SpringKafkaDefaultHeaders");
    }

    public AsyncHeadersForSpringKafkaBuilder(String schemaName) {
        this.headers = new SchemaObject();
        this.headers.setType("object");
        this.headers.setTitle(schemaName);
        this.headers.setProperties(new HashMap<>());
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
        SchemaObject header = new SchemaObject();
        header.setType("string");
        header.setTitle(headerName);
        header.setDescription(description);
        header.setExamples(List.of(exampleType));
        header.setEnumValues(types);

        headers.getProperties().put(headerName, header);
        return this;
    }

    public SchemaObject build() {
        return this.headers;
    }
}
