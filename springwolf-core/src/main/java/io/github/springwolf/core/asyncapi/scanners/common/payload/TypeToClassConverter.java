// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.HashMap;
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
        // this uses the error boundary pattern
        try {
            return extractClassUnsafe(parameterType);
        } catch (Exception ex) {
            log.debug("Unable to extract class for type {}", parameterType.getTypeName(), ex);
        }

        return Void.class;
    }

    public Class<?> extractClassUnsafe(Type parameterType) {
        if (parameterType instanceof ParameterizedType parameterTypeParameterized) {
            Type rawParameterType = parameterTypeParameterized.getRawType();

            Class<?> actualPayloadClass =
                    extractActualGenericClass(parameterTypeParameterized, rawParameterType.getTypeName());
            if (actualPayloadClass != Void.class) {
                return actualPayloadClass;
            }

            // nested generic class - fallback to most outer container
            String typeName = getTypeName(rawParameterType, rawParameterType.getTypeName());
            return loadClass(typeName);
        }

        // no generics used - just a normal type
        String typeName = getTypeName(parameterType, parameterType.getTypeName());
        return loadClass(typeName);
    }

    private Class<?> extractActualGenericClass(ParameterizedType parameterType, String rawParameterTypeName) {
        Type type = parameterType;
        String typeName = rawParameterTypeName;

        while (type instanceof ParameterizedType typeParameterized
                && extractableClassToArgumentIndex.containsKey(typeName)) {
            Integer index = extractableClassToArgumentIndex.get(typeName);

            type = typeParameterized.getActualTypeArguments()[index];
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

            typeName = getTypeName(type, typeName);
        }

        return loadClass(typeName);
    }

    private Class<?> loadClass(String typeName) {
        try {
            return ClassForNameUtil.forName(typeName);
        } catch (ClassNotFoundException ex) {
            log.debug("Unable to load class for type {}", typeName, ex);
        }

        return Void.class;
    }

    private String getTypeName(Type type, String fallback) {
        if (type instanceof Class && ((Class<?>) type).getComponentType() != null) {
            return ((Class<?>) type).componentType().getTypeName();
        }
        return fallback;
    }

    public static final class ClassForNameUtil {
        private ClassForNameUtil() {}

        private static final Map<String, Class<?>> PRIMITIVE_CLASSES;

        static {
            Class<?>[] classes = {
                void.class,
                boolean.class,
                char.class,
                byte.class,
                short.class,
                int.class,
                long.class,
                float.class,
                double.class
            };

            PRIMITIVE_CLASSES = new HashMap<>();
            for (Class<?> cls : classes) {
                PRIMITIVE_CLASSES.put(cls.getName(), cls);
            }
        }

        public static Class<?> forName(final String name) throws ClassNotFoundException {
            Class<?> clazz = PRIMITIVE_CLASSES.get(name);

            if (clazz != null) {
                return clazz;
            }

            return Class.forName(name);
        }
    }
}
