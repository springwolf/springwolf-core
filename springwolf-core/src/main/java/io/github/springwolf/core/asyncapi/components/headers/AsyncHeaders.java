// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

import io.swagger.v3.oas.models.media.Schema;

import java.util.HashMap;

public class AsyncHeaders extends HashMap<String, Schema> {
    /**
     * Alias to stay backwards-compatible
     */
    public static final AsyncHeaders NOT_DOCUMENTED = AsyncHeadersNotDocumented.NOT_DOCUMENTED;
    /**
     * Explicitly document that no headers are used.
     */
    public static final AsyncHeaders NOT_USED = new AsyncHeaders("HeadersNotUsed", "No headers are present.");

    private final String schemaName;
    private final String description;

    public AsyncHeaders(String schemaName, String description) {
        this.schemaName = schemaName;
        this.description = description;
    }

    public AsyncHeaders(String schemaName) {
        this(schemaName, null);
    }

    public String getSchemaName() {
        return this.schemaName;
    }

    public String getDescription() {
        return this.description;
    }

    public void addHeader(AsyncHeaderSchema header) {
        this.put(header.getHeaderName(), header);
    }

    public static AsyncHeaders from(AsyncHeaders source, String newSchemaName) {
        AsyncHeaders clone = new AsyncHeaders(newSchemaName, source.getDescription());
        clone.putAll(source);
        return clone;
    }
}
