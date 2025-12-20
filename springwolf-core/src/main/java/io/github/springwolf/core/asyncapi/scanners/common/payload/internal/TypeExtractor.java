// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Map;

@Slf4j
public class TypeExtractor {

    private final Map<String, Integer> extractableClassToArgumentIndex;

    public TypeExtractor(SpringwolfConfigProperties properties) {
        if (properties.getPayload() != null) {
            extractableClassToArgumentIndex = properties.getPayload().getExtractableClasses();
        } else {
            extractableClassToArgumentIndex = Map.of();
        }
    }

    public Type extractActualType(Type parameterType) {
        // this uses the error boundary pattern
        try {
            return extractActualTypeUnsafe(parameterType);
        } catch (Exception ex) {
            log.debug("Unable to extract class for type {}", parameterType.getTypeName(), ex);
        }

        return Void.class;
    }

    private Type extractActualTypeUnsafe(Type parameterType) {
        Type type = parameterType;

        while (type instanceof ParameterizedType typeParameterized) {
            String typeName = typeParameterized.getRawType().getTypeName();
            if (!extractableClassToArgumentIndex.containsKey(typeName)) {
                break;
            }

            Integer index = extractableClassToArgumentIndex.get(typeName);
            type = typeParameterized.getActualTypeArguments()[index];

            if (type instanceof WildcardType wildcardType) {
                Type[] upperBounds = wildcardType.getUpperBounds();
                Type[] lowerBounds = wildcardType.getLowerBounds();
                if (upperBounds.length > 0 && upperBounds[0] != Object.class) {
                    type = upperBounds[0];
                }
                if (lowerBounds.length > 0 && lowerBounds[0] != Object.class) {
                    type = lowerBounds[0];
                }
            }
        }

        return type;
    }
}
