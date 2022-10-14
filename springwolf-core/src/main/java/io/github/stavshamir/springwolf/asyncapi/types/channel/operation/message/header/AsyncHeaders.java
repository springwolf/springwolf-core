package io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header;

import io.swagger.v3.oas.models.media.Schema;

import java.util.HashMap;

public class AsyncHeaders extends HashMap<String, Schema> {
    /**
     * Per default, if no headers are explicitly defined, NOT_DOCUMENTED is used.
     * There can be headers, but don't have to be.
     */
    public final static AsyncHeaders NOT_DOCUMENTED = new AsyncHeaders("HeadersNotDocumented");
    /**
     * Explicitly document that no headers are used.
     */
    public final static AsyncHeaders NOT_USED = new AsyncHeaders("HeadersNotUsed");

    private final String schemaName;

    public AsyncHeaders(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return this.schemaName;
    }

    public void addHeader(AsyncHeaderSchema header) {
        this.put(header.getHeaderName(), header);
    }

    public static AsyncHeaders from(AsyncHeaders source, String newSchemaName) {
        AsyncHeaders clone = new AsyncHeaders(newSchemaName);
        clone.putAll(source);
        return clone;
    }
}
