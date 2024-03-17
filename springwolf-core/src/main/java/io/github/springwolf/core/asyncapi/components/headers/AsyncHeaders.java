// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.swagger.v3.oas.models.media.Schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class AsyncHeaders extends HashMap<String, Schema> {
    /**
     * Alias to stay backwards-compatible
     */
    public static final SchemaObject NOT_DOCUMENTED = AsyncHeadersNotDocumented.NOT_DOCUMENTED;
    /**
     * Explicitly document that no headers are used.
     */
    public static final SchemaObject NOT_USED = new SchemaObject();

    static {
        NOT_USED.setType("object");
        NOT_USED.setTitle("HeadersNotUsed");
        NOT_USED.setProperties(Map.of());
        NOT_USED.setExamples(List.of(new Object()));
    }

    private final String schemaName;

    public AsyncHeaders(String schemaName) {
        this.schemaName = schemaName;
    }

    @Deprecated
    public String getSchemaName() {
        return this.schemaName;
    }

    @Deprecated
    public void addHeader(AsyncHeaderSchema header) {
        this.put(header.getHeaderName(), header);
    }

    @Deprecated
    public static AsyncHeaders from(AsyncHeaders source, String newSchemaName) {
        AsyncHeaders clone = new AsyncHeaders(newSchemaName);
        clone.putAll(source);
        return clone;
    }
}
