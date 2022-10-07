package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import org.springframework.kafka.support.converter.AbstractJavaTypeMapper;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;

public class AsyncHeadersForSpringKafkaBuilder {
    private final AsyncHeaders headers;

    public AsyncHeadersForSpringKafkaBuilder() {
        this("SpringDefaultHeaders");
    }

    public AsyncHeadersForSpringKafkaBuilder(String schemaName) {
        this.headers = new AsyncHeaders(schemaName);
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId) {
        return withTypeIdHeader(exampleTypeId, of(exampleTypeId));
    }

    public AsyncHeadersForSpringKafkaBuilder withTypeIdHeader(String exampleTypeId, List<String> types) {
        return this.withHeader(
                AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME,
                types,
                exampleTypeId,
                "Spring Type Id Header"
        );
    }

    private AsyncHeadersForSpringKafkaBuilder withHeader(String headerName, List<String> types, String exampleType, String description) {
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
