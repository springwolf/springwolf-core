// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.components.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;

import java.util.List;
import java.util.Map;

public class AsyncHeadersNotDocumented implements AsyncHeadersBuilder {
    /**
     * Per default, if no headers are explicitly defined, {@link AsyncHeadersNotUsed#NOT_USED} is used.
     * There can be headers, but don't have to be.
     */
    public static final SchemaObject NOT_DOCUMENTED = new SchemaObject();

    static {
        NOT_DOCUMENTED.setType("object");
        NOT_DOCUMENTED.setTitle("HeadersNotDocumented");
        NOT_DOCUMENTED.setDescription("There can be headers, but they are not explicitly documented.");
        NOT_DOCUMENTED.setProperties(Map.of());
        NOT_DOCUMENTED.setExamples(List.of(new Object()));
    }

    @Override
    public SchemaObject buildHeaders(Class<?> payloadType) {
        return NOT_DOCUMENTED;
    }
}
