// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaService;
import io.github.springwolf.core.asyncapi.schemas.SwaggerSchemaUtil;
import io.swagger.v3.oas.models.media.Schema;
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
    private final SwaggerSchemaUtil swaggerSchemaUtil;

    public SchemaObject extractHeader(Method method, NamedSchemaObject payload) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Extract header for {}", methodName);

        SchemaObject headers = SchemaObject.builder()
                .title(payload.name() + "Headers")
                .properties(new HashMap<>())
                .build();

        for (Parameter argument : method.getParameters()) {
            if (argument.isAnnotationPresent(Header.class)) {
                Header headerAnnotation = argument.getAnnotation(Header.class);
                String headerName = getHeaderAnnotationName(headerAnnotation);

                SchemaObject schema = getHeaderSchema(argument);

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

    private SchemaObject getHeaderSchema(Parameter argument) {
        Schema swaggerSchema = schemaService.createSchema(argument.getType()).getSchema();
        return swaggerSchemaUtil.mapSchema(swaggerSchema);
    }
}
