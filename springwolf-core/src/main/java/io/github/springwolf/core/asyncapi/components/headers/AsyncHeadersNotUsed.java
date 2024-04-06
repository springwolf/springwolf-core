// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

import java.util.List;
import java.util.Map;

public class AsyncHeadersNotUsed implements AsyncHeadersBuilder {
    /**
     * Explicitly document that no headers are used.
     */
    public static final SchemaObject NOT_USED = new SchemaObject();

    static {
        NOT_USED.setType("object");
        NOT_USED.setTitle("HeadersNotUsed");
        NOT_USED.setDescription("No headers are present.");
        NOT_USED.setProperties(Map.of());
        NOT_USED.setExamples(List.of(new Object()));
    }

    @Override
    public SchemaObject buildHeaders(Class<?> payloadType) {
        return NOT_USED;
    }
}
