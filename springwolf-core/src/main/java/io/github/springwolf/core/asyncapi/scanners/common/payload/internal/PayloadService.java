// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class PayloadService {
    private final ComponentsService componentsService;
    private final SpringwolfConfigProperties properties;

    private static final String PAYLOAD_NOT_USED_KEY = "PayloadNotUsed";
    public static final NamedSchemaObject PAYLOAD_NOT_USED = new NamedSchemaObject(
            PAYLOAD_NOT_USED_KEY,
            SchemaObject.builder()
                    .title(PAYLOAD_NOT_USED_KEY)
                    .description("No payload specified")
                    .properties(Map.of())
                    .build());

    public NamedSchemaObject buildSchema(Class<?> payloadType) {
        String contentType = properties.getDocket().getDefaultContentType();

        return buildSchema(contentType, payloadType);
    }

    public NamedSchemaObject buildSchema(String contentType, Class<?> payloadType) {
        String componentsSchemaName = this.componentsService.registerSchema(payloadType, contentType);

        SchemaObject schema = componentsService.resolveSchema(componentsSchemaName);
        if (schema != null) {
            schema.setTitle(payloadType.getSimpleName());
        }

        return new NamedSchemaObject(componentsSchemaName, schema);
    }

    public NamedSchemaObject useUnusedPayload() {
        SchemaObject schema = PAYLOAD_NOT_USED.schema();
        if (schema != null) {
            this.componentsService.registerSchema(schema);
        }
        return PAYLOAD_NOT_USED;
    }
}
