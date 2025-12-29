// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.components.ComponentSchema;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaType;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Set;

@Slf4j
@AllArgsConstructor
public class HeaderClassExtractor {
    private final SwaggerSchemaService schemaService;

    public SchemaObject extractHeader(Method method, PayloadSchemaObject payload) {
        String methodName = "%s::%s".formatted(method.getDeclaringClass().getSimpleName(), method.getName());
        log.trace("Extract header for {}", methodName);

        SchemaObject headers = SchemaObject.builder()
                .type(Set.of(SchemaType.OBJECT))
                .title(payload.name() + "Headers")
                .properties(new HashMap<>())
                .build();

        for (Parameter argument : method.getParameters()) {
            if (argument.isAnnotationPresent(Header.class)) {
                Header headerAnnotation = argument.getAnnotation(Header.class);
                String headerName = getHeaderAnnotationName(headerAnnotation);

                SwaggerSchemaService.ExtractedSchemas extractedSchema =
                        schemaService.postProcessSimpleSchema(argument.getType());
                ComponentSchema rootComponentSchema = extractedSchema.rootSchema();

                // to stay compatible with former versions.
                // Only simple header schemas are supported, which are used inline and do not require to be registered
                // in the components block.
                ComponentSchema headerSchema = null;
                if (rootComponentSchema.getSchema() != null) {
                    headerSchema = rootComponentSchema;
                } else {
                    if (extractedSchema.referencedSchemas().size() == 1) {
                        headerSchema = extractedSchema
                                .referencedSchemas()
                                .values()
                                .iterator()
                                .next();
                    }
                }

                if (headerSchema != null) {
                    headers.getProperties().put(headerName, headerSchema);
                } else {
                    log.debug("Unable to extract schema for header {} in method {}", headerName, methodName);
                }
            }
        }

        if (headers.getProperties().isEmpty()) {
            return AsyncHeadersNotDocumented.NOT_DOCUMENTED;
        }

        return headers;
    }

    private static String getHeaderAnnotationName(Header annotation) {
        if (!annotation.name().isBlank()) {
            return annotation.name();
        }

        return annotation.value();
    }
}
