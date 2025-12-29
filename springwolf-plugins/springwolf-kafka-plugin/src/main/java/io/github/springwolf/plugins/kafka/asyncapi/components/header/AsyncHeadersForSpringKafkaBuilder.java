// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.components.header;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import org.springframework.kafka.support.mapping.DefaultJacksonJavaTypeMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AsyncHeadersForSpringKafkaBuilder {
    private final SchemaObject headers;

    public AsyncHeadersForSpringKafkaBuilder() {
        this("SpringKafkaDefaultHeaders");
    }

    public AsyncHeadersForSpringKafkaBuilder(String schemaName) {
        this.headers = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .title(schemaName)
                .properties(new HashMap<>())
                .build();
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId) {
        return withTypeIdHeader(exampleTypeId, List.of(exampleTypeId));
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId, List<String> types) {
        return this.withHeader(
                DefaultJacksonJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME, types, exampleTypeId, "Spring Type Id Header");
    }

    private AsyncHeadersForSpringKafkaBuilder withHeader(
            String headerName, List<String> types, String exampleType, String description) {
        SchemaObject header = new SchemaObject();
        header.setType(Set.of(SchemaType.STRING));
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
