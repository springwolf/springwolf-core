package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import org.springframework.http.MediaType;
import org.springframework.util.MimeType;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static java.util.stream.Collectors.toList;

public class AsyncHeadersForCloudEventsBuilder {

    private final AsyncHeaders headers;

    public AsyncHeadersForCloudEventsBuilder() {
        this("CloudEventsBase");
    }

    public AsyncHeadersForCloudEventsBuilder(String schemaName) {
        this.headers = new AsyncHeaders(schemaName);
    }

    public AsyncHeadersForCloudEventsBuilder(String newSchemaName, AsyncHeaders baseHeaders) {
        this.headers = AsyncHeaders.from(baseHeaders, newSchemaName);
    }

    public AsyncHeadersForCloudEventsBuilder withContentTypeHeader(MediaType contentType) {
        return withContentTypeHeader(contentType, of(contentType));
    }

    public AsyncHeadersForCloudEventsBuilder withContentTypeHeader(MediaType exampleContentType, List<MediaType> contentTypeValues) {
        List<String> contentTypeStringValues = contentTypeValues.stream().map(MimeType::toString).collect(toList());
        return withHeader(
                "content-type",
                contentTypeStringValues,
                exampleContentType.toString(),
                "CloudEvent Content-Type Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withSpecVersionHeader(String specVersion) {
        return withSpecVersionHeader(specVersion, of(specVersion));
    }

    public AsyncHeadersForCloudEventsBuilder withSpecVersionHeader(String specVersion, List<String> specValues) {
        return withHeader(
                "ce_specversion",
                specValues,
                specVersion,
                "CloudEvent Spec Version Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withIdHeader(String idExample) {
        return withIdHeader(idExample, of(idExample));
    }

    public AsyncHeadersForCloudEventsBuilder withIdHeader(String idExample, List<String> idValues) {
        return withHeader(
                "ce_id",
                idValues,
                idExample,
                "CloudEvent Id Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withTimeHeader(String timeExample) {
        return withTimeHeader(timeExample, of(timeExample));
    }

    public AsyncHeadersForCloudEventsBuilder withTimeHeader(String timeExample, List<String> timeValues) {
        return withHeader(
                "ce_time",
                timeValues,
                timeExample,
                "CloudEvent Time Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withTypeHeader(String typeExample) {
        return withTypeHeader(typeExample, of(typeExample));
    }

    public AsyncHeadersForCloudEventsBuilder withTypeHeader(String typeExample, List<String> typeValues) {
        return withHeader(
                "ce_type",
                typeValues,
                typeExample,
                "CloudEvent Payload Type Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withSourceHeader(String sourceExample) {
        return withSourceHeader(sourceExample, of(sourceExample));
    }

    public AsyncHeadersForCloudEventsBuilder withSourceHeader(String sourceExample, List<String> sourceValues) {
        return withHeader(
                "ce_source",
                sourceValues,
                sourceExample,
                "CloudEvent Source Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withSubjectHeader(String subjectExample) {
        return withSubjectHeader(subjectExample, of(subjectExample));
    }

    public AsyncHeadersForCloudEventsBuilder withSubjectHeader(String subjectExample, List<String> subjectValues) {
        return withHeader(
                "ce_subject",
                subjectValues,
                subjectExample,
                "CloudEvent Subject Header"
        );
    }

    public AsyncHeadersForCloudEventsBuilder withExtension(String headerName, List<String> values, String exampleValue, String description) {
        return withHeader(headerName, values, exampleValue, description);
    }

    private AsyncHeadersForCloudEventsBuilder withHeader(String headerName, List<String> values, String exampleValue, String description) {
        AsyncHeaderSchema header = AsyncHeaderSchema.headerBuilder()
                .headerName(headerName)
                .description(description)
                .example(exampleValue)
                .enumValue(values)
                .build();
        headers.addHeader(header);
        return this;
    }

    public AsyncHeaders build() {
        return AsyncHeaders.from(this.headers, this.headers.getSchemaName());
    }

}
