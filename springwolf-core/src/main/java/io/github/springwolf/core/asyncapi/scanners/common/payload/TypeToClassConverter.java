// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
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
            log.info("Unable to extract generic data type of {}", parameterType, ex);
        }
        return Void.class;
    }

    private Class<?> extractActualGenericClass(ParameterizedType parameterType, String rawParameterTypeName) {
        Type type = parameterType;
        String typeName = rawParameterTypeName;

        while (type instanceof ParameterizedType && extractableClassToArgumentIndex.containsKey(typeName)) {
            Integer index = extractableClassToArgumentIndex.get(typeName);

            type = ((ParameterizedType) type).getActualTypeArguments()[index];
            typeName = type.getTypeName();

            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                Type[] lowerBounds = ((WildcardType) type).getLowerBounds();
                if (upperBounds.length > 0 && upperBounds[0] != Object.class) {
                    type = upperBounds[0];
                    typeName = upperBounds[0].getTypeName();
                }
                if (lowerBounds.length > 0 && lowerBounds[0] != Object.class) {
                    type = lowerBounds[0];
                    typeName = lowerBounds[0].getTypeName();
                }
            }

            if (type instanceof ParameterizedType) {
                typeName = ((ParameterizedType) type).getRawType().getTypeName();
            }
        }

        try {
            return Class.forName(typeName);
        } catch (ClassNotFoundException ex) {
            log.debug("Unable to find class for type {}", typeName, ex);
        }

        return Void.class;
    }
}
