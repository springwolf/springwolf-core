// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Slf4j
public class TypeToClassConverter {

    private final Map<String, Integer> extractableClassToArgumentIndex;

    public TypeToClassConverter(SpringwolfConfigProperties properties) {
        if (properties.getPayload() != null) {
            extractableClassToArgumentIndex = properties.getPayload().getExtractableClasses();
        } else {
            extractableClassToArgumentIndex = Map.of();
        }
    }

    public Class<?> extractClass(Type parameterType) {
        try {
            if (parameterType instanceof ParameterizedType) {
                Type rawParameterType = ((ParameterizedType) parameterType).getRawType();
                String rawParameterTypeName = rawParameterType.getTypeName();

                Class<?> actualPayloadClass =
                        extractActualGenericClass((ParameterizedType) parameterType, rawParameterTypeName);
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

    private Class<?> extractActualGenericClass(ParameterizedType parameterType, String rawParameterTypeName) {
        Type type = parameterType;
        String typeName = rawParameterTypeName;

        while (type instanceof ParameterizedType && extractableClassToArgumentIndex.containsKey(typeName)) {
            Integer index = extractableClassToArgumentIndex.get(rawParameterTypeName);

            type = ((ParameterizedType) type).getActualTypeArguments()[index];

            typeName = type.getTypeName();
            if (type instanceof ParameterizedType) {
                typeName = ((ParameterizedType) type).getRawType().getTypeName();
            }
        }

        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException ex) {
            log.debug("Unable to find class for type %s".formatted(typeName), ex);
        }

        return Void.class;
    }
}
