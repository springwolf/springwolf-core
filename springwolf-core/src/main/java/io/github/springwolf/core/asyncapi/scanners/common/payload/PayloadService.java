// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PayloadService {
    private final PayloadClassExtractor payloadClassExtractor;
    private final ComponentsService componentsService;
    private final SpringwolfConfigProperties properties;

    private static final String PAYLOAD_NOT_USED_KEY = "PayloadNotUsed";
    static final NamedSchemaObject PAYLOAD_NOT_USED = new NamedSchemaObject(
            PAYLOAD_NOT_USED_KEY,
            SchemaObject.builder()
                    .title(PAYLOAD_NOT_USED_KEY)
                    .description("No payload specified")
                    .properties(Map.of())
                    .build());

    public NamedSchemaObject extractSchema(Method method) {
        Optional<Class<?>> payloadType = payloadClassExtractor.extractFrom(method);

        return payloadType.map(this::buildSchema).orElseGet(this::useUnusedPayload);
    }

    public NamedSchemaObject extractSchema(AsyncOperation operationData, Method method) {
        Optional<Class<?>> payloadType = operationData.payloadType() != Object.class
                ? Optional.of(operationData.payloadType())
                : payloadClassExtractor.extractFrom(method);

        String contentType = operationData.message().contentType();

        return payloadType.map((type) -> buildSchema(contentType, type)).orElseGet(this::useUnusedPayload);
    }

    public NamedSchemaObject buildSchema(Class<?> payloadType) {
        String contentType = properties.getDocket().getDefaultContentType();

        return buildSchema(contentType, payloadType);
    }

    private NamedSchemaObject buildSchema(String contentType, Class<?> payloadType) {
        String componentsSchemaName = this.componentsService.registerSchema(payloadType, contentType);

        SchemaObject schema = componentsService.resolveSchema(componentsSchemaName);
        schema.setTitle(payloadType.getSimpleName());

        return new NamedSchemaObject(componentsSchemaName, schema);
    }

    private NamedSchemaObject useUnusedPayload() {
        this.componentsService.registerSchema(PAYLOAD_NOT_USED.schema());
        return PAYLOAD_NOT_USED;
    }
}
