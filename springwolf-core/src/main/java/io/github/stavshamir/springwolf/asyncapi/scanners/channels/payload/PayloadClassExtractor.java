// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

import io.github.stavshamir.springwolf.configuration.properties.SpringwolfConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

@Slf4j
public class PayloadClassExtractor {
    private final TypeToClassConverter typeToClassConverter;

    public PayloadClassExtractor(SpringwolfConfigProperties properties) {
        Map<String, Integer> extractableClasses = Map.of();
        if (properties.getPayload() != null) {
            extractableClasses = properties.getPayload().getExtractableClasses();
        }
        typeToClassConverter = new TypeToClassConverter(extractableClasses);
    }

    public Class<?> extractFrom(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        int parameterPayloadIndex =
                getPayloadParameterIndex(method.getParameterTypes(), method.getParameterAnnotations(), methodName);

        return typeToClassConverter.extractClass(method.getGenericParameterTypes()[parameterPayloadIndex]);
    }

    public Class<?> typeToClass(Type type) {
        return typeToClassConverter.extractClass(type);
    }

    private int getPayloadParameterIndex(
            Class<?>[] parameterClasses, Annotation[][] parameterAnnotations, String methodName) {
        switch (parameterClasses.length) {
            case 0 -> throw new IllegalArgumentException("Listener methods must not have 0 parameters: " + methodName);
            case 1 -> {
                return 0;
            }
            default -> {
                int payloadAnnotatedParameterIndex = getPayloadAnnotatedParameterIndex(parameterAnnotations);
                if (payloadAnnotatedParameterIndex == -1) {
                    String msg =
                            "Multi-parameter AsyncListener methods must have one parameter annotated with @Payload, "
                                    + "but none was found: "
                                    + methodName;

                    throw new IllegalArgumentException(msg);
                }
                return payloadAnnotatedParameterIndex;
            }
        }
    }

    private int getPayloadAnnotatedParameterIndex(Annotation[][] parameterAnnotations) {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            boolean hasPayloadAnnotation = Arrays.stream(annotations).anyMatch(Payload.class::isInstance);

            if (hasPayloadAnnotation) {
                return i;
            }
        }

        return -1;
    }

    @RequiredArgsConstructor
    private static class TypeToClassConverter {

        private final Map<String, Integer> extractableClassToArgumentIndex;

        private Class<?> extractClass(Type parameterType) {
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
}
