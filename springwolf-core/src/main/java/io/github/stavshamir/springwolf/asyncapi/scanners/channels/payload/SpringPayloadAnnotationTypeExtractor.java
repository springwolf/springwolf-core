// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload;

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
@RequiredArgsConstructor
public class SpringPayloadAnnotationTypeExtractor {

    private final Map<String, Integer> genericClassesToExtract = Map.of(
            "java.util.List",
            0,
            "org.springframework.messaging.Message",
            0,
            "org.apache.kafka.streams.kstream.KStream",
            1);

    public Class<?> getPayloadType(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        int parameterPayloadIndex =
                getPayloadParameterIndex(method.getParameterTypes(), method.getParameterAnnotations(), methodName);

        return getActualPayloadClass(method.getGenericParameterTypes()[parameterPayloadIndex]);
    }

    public Class<?> typeToClass(Type type) {
        return getActualPayloadClass(type);
    }

    private Class<?> getActualPayloadClass(Type parameterType) {
        try {
            if (parameterType instanceof ParameterizedType) {
                Type rawParameterType = ((ParameterizedType) parameterType).getRawType();
                String rawParameterTypeName = rawParameterType.getTypeName();

                Class<?> actualPayloadClass =
                        getActualNestedPayloadClass((ParameterizedType) parameterType, rawParameterTypeName);
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

    private Class<?> getActualNestedPayloadClass(ParameterizedType parameterType, String rawParameterTypeName) {
        Type type = parameterType;
        String typeName = rawParameterTypeName;

        while (type instanceof ParameterizedType && genericClassesToExtract.containsKey(typeName)) {
            Integer index = genericClassesToExtract.get(rawParameterTypeName);

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
}