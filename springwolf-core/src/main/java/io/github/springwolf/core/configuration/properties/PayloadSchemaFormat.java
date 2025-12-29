// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.configuration.properties;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaFormat;
import lombok.Getter;

/**
 * Enumeration defining the supported payload schema formats, for use in {@link SpringwolfConfigProperties}.
 */
@Getter
public enum PayloadSchemaFormat {
    ASYNCAPI_V3(SchemaFormat.ASYNCAPI_V3),
    OPENAPI_V3(SchemaFormat.OPENAPI_V3),
    OPENAPI_V3_1(SchemaFormat.OPENAPI_V3_1);

    private final SchemaFormat schemaFormat;

    PayloadSchemaFormat(SchemaFormat schemaFormat) {
        this.schemaFormat = schemaFormat;
    }
}
