// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.headers;

import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.TypeToClassConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

@Slf4j
@AllArgsConstructor
public class HeaderClassExtractor {
    private final TypeToClassConverter typeToClassConverter;

    public SchemaObject extractFrom(Method method, NamedSchemaObject payload) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding header types for {}", methodName);

        SchemaObject headers = SchemaObject.builder()
                .title(payload.name() + "Headers")
                .properties(new HashMap<>())
                .build();

        for (Parameter argument : method.getParameters()) {
            if (argument.isAnnotationPresent(Header.class)) {
                Header headerAnnotation = argument.getAnnotation(Header.class);
                var clazz = typeToClassConverter.extractClass(argument.getType());

                String headerName = getHeaderAnnotationName(headerAnnotation);
                SchemaObject headerSchema = getSchema(clazz);
                headers.getProperties().put(headerName, headerSchema);
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

    private static SchemaObject getSchema(Class<?> type) {
        // FIXME: merge/extract with code from DefaultComponentsService
        if (type.equals(String.class) || type.equals(Character.class) || type.equals(Byte.class)) {
            return SchemaObject.builder().type("string").build();
        } else if (Boolean.class.isAssignableFrom(type)) {
            return SchemaObject.builder().type("boolean").build();
        } else if (Number.class.isAssignableFrom(type)) {
            return SchemaObject.builder().type("number").build();
        }
        return SchemaObject.builder().type("object").build();
    }
}
