// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SchemasService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class PayloadService {
    private final SchemasService schemasService;
    private final SpringwolfConfigProperties properties;

    private static final String PAYLOAD_NOT_USED_KEY = "PayloadNotUsed";
    public static final PayloadSchemaObject PAYLOAD_NOT_USED = new PayloadSchemaObject(
            PAYLOAD_NOT_USED_KEY,
            PAYLOAD_NOT_USED_KEY,
            ComponentSchema.of(SchemaObject.builder()
                    .type(Set.of(SchemaType.OBJECT.getValue()))
                    .title(PAYLOAD_NOT_USED_KEY)
                    .description("No payload specified")
                    .properties(Map.of())
                    .build()));

    public PayloadSchemaObject buildSchema(Type payloadType) {
        String contentType = properties.getDocket().getDefaultContentType();

        return buildSchema(contentType, payloadType);
    }

    /**
     * creates a {@link PayloadSchemaObject} from the given type and content type. Registers the created schema objects
     * with this {@link SchemasService}.
     */
    public PayloadSchemaObject buildSchema(String contentType, Type payloadType) {
        String schemaName = schemasService.getSchemaName(payloadType);
        String simpleSchemaName = schemasService.getSimpleSchemaName(payloadType);

        ComponentSchema schema = schemasService.registerType(payloadType, contentType);
        return new PayloadSchemaObject(schemaName, simpleSchemaName, schema);
    }

    public PayloadSchemaObject useUnusedPayload() {
        ComponentSchema schema = PAYLOAD_NOT_USED.schema();
        if (schema != null && schema.getSchema() != null) {
            this.schemasService.registerSchema(schema.getSchema());
        }
        return PAYLOAD_NOT_USED;
    }
}
