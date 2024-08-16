// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

@Slf4j
@AllArgsConstructor
public class HeaderClassExtractor {
    private final SwaggerSchemaService schemaService;

    public SchemaObject extractHeader(Method method, PayloadSchemaObject payload) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Extract header for {}", methodName);

        SchemaObject headers = SchemaObject.builder()
                .type("object")
                .title(payload.name() + "Headers")
                .properties(new HashMap<>())
                .build();

        for (Parameter argument : method.getParameters()) {
            if (argument.isAnnotationPresent(Header.class)) {
                Header headerAnnotation = argument.getAnnotation(Header.class);
                String headerName = getHeaderAnnotationName(headerAnnotation);

                SchemaObject schema =
                        schemaService.extractSchema(argument.getType()).getRootSchema();

                headers.getProperties().put(headerName, schema);
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
