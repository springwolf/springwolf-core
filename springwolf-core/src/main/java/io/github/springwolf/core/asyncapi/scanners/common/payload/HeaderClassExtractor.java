// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.asyncapi.components.headers.AsyncHeaders;
import io.github.springwolf.core.asyncapi.components.headers.AsyncHeadersNotDocumented;
import io.swagger.v3.oas.models.media.BooleanSchema;
import io.swagger.v3.oas.models.media.IntegerSchema;
import io.swagger.v3.oas.models.media.MapSchema;
import io.swagger.v3.oas.models.media.NumberSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Slf4j
public class HeaderClassExtractor {
    private HeaderClassExtractor() {}

    public static AsyncHeaders extractFrom(Method method, Class<?> payload) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding header types for {}", methodName);

        AsyncHeaders headers = new AsyncHeaders(payload.getName() + "Headers");

        for (Parameter argument : method.getParameters()) {
            if (argument.isAnnotationPresent(Header.class)) {
                Header headerAnnotation = argument.getAnnotation(Header.class);
                var clazz = TypeToClassConverter.extractClass(argument.getType());
                headers.put(getHeaderAnnotationName(headerAnnotation), getSchema(clazz));
            } else if (argument.isAnnotationPresent(Headers.class)) {
                var clazz = TypeToClassConverter.extractClass(argument.getType());
                headers.put(argument.getName(), getSchema(clazz));
            }
        }

        if (headers.isEmpty()) {
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

    private static Schema<?> getSchema(Class<?> clazz) {
        if (clazz.equals(String.class)) {
            return new StringSchema();
        } else if (clazz.equals(Boolean.class)) {
            return new BooleanSchema();
        } else if (clazz.equals(Integer.class)) {
            return new IntegerSchema();
        } else if (clazz.equals(Number.class)) {
            return new NumberSchema();
        }
        return new MapSchema();
    }

    private record TypeToClassConverter() {

        private static Class<?> extractClass(Type parameterType) {
            try {
                if (parameterType instanceof ParameterizedType parameterizedType) {
                    Type rawParameterType = parameterizedType.getRawType();
                    String rawParameterTypeName = rawParameterType.getTypeName();

                    Class<?> actualPayloadClass = extractActualGenericClass(rawParameterTypeName);
                    if (actualPayloadClass != Void.class) {
                        return actualPayloadClass;
                    }

                    // nested generic class - fallback to most outer container
                    return Class.forName(rawParameterTypeName);
                }

                // no generics used - just a normal type
                return Class.forName(parameterType.getTypeName());
            } catch (Exception ex) {
                log.info("Unable to extract generic data type of %s".formatted(parameterType), ex);
            }
            return Void.class;
        }

        private static Class<?> extractActualGenericClass(String rawParameterTypeName) {

            try {
                return Class.forName(rawParameterTypeName);
            } catch (ClassNotFoundException ex) {
                log.debug("Unable to find class for type %s".formatted(rawParameterTypeName), ex);
            }

            return Void.class;
        }
    }
}
