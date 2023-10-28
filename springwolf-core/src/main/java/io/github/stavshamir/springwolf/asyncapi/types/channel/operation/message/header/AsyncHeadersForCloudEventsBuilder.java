// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import org.springframework.http.MediaType;
import org.springframework.util.MimeType;

import java.util.List;

/**
 * Only used in combination with AsyncApiDocket bean
 * If not, let us know by raising a GitHub issue
 */
@Deprecated
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
        return withContentTypeHeader(contentType, List.of(contentType));
    }

    public AsyncHeadersForCloudEventsBuilder withContentTypeHeader(
            MediaType exampleContentType, List<MediaType> contentTypeValues) {
        List<String> contentTypeStringValues =
                contentTypeValues.stream().map(MimeType::toString).toList();
        return withHeader(
                AsyncHeadersCloudEventConstants.CONTENT_TYPE,
                contentTypeStringValues,
                exampleContentType.toString(),
                AsyncHeadersCloudEventConstants.CONTENT_TYPE_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withSpecVersionHeader(String specVersion) {
        return withSpecVersionHeader(specVersion, List.of(specVersion));
    }

    public AsyncHeadersForCloudEventsBuilder withSpecVersionHeader(String specVersion, List<String> specValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.SPECVERSION,
                specValues,
                specVersion,
                AsyncHeadersCloudEventConstants.SPECVERSION_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withIdHeader(String idExample) {
        return withIdHeader(idExample, List.of(idExample));
    }

    public AsyncHeadersForCloudEventsBuilder withIdHeader(String idExample, List<String> idValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.ID, idValues, idExample, AsyncHeadersCloudEventConstants.ID_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withTimeHeader(String timeExample) {
        return withTimeHeader(timeExample, List.of(timeExample));
    }

    public AsyncHeadersForCloudEventsBuilder withTimeHeader(String timeExample, List<String> timeValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.TIME,
                timeValues,
                timeExample,
                AsyncHeadersCloudEventConstants.TIME_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withTypeHeader(String typeExample) {
        return withTypeHeader(typeExample, List.of(typeExample));
    }

    public AsyncHeadersForCloudEventsBuilder withTypeHeader(String typeExample, List<String> typeValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.TYPE,
                typeValues,
                typeExample,
                AsyncHeadersCloudEventConstants.TYPE_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withSourceHeader(String sourceExample) {
        return withSourceHeader(sourceExample, List.of(sourceExample));
    }

    public AsyncHeadersForCloudEventsBuilder withSourceHeader(String sourceExample, List<String> sourceValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.SOURCE,
                sourceValues,
                sourceExample,
                AsyncHeadersCloudEventConstants.SOURCE_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withSubjectHeader(String subjectExample) {
        return withSubjectHeader(subjectExample, List.of(subjectExample));
    }

    public AsyncHeadersForCloudEventsBuilder withSubjectHeader(String subjectExample, List<String> subjectValues) {
        return withHeader(
                AsyncHeadersCloudEventConstants.SUBJECT,
                subjectValues,
                subjectExample,
                AsyncHeadersCloudEventConstants.SUBJECT_DESC);
    }

    public AsyncHeadersForCloudEventsBuilder withExtension(
            String headerName, List<String> values, String exampleValue, String description) {
        return withHeader(headerName, values, exampleValue, description);
    }

    private AsyncHeadersForCloudEventsBuilder withHeader(
            String headerName, List<String> values, String exampleValue, String description) {
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
