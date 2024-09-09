// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload.internal;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class PayloadClassExtractor {
    private final Map<String, Integer> extractableClassToArgumentIndex;

    public PayloadClassExtractor(SpringwolfConfigProperties properties) {
        if (properties.getPayload() != null) {
            extractableClassToArgumentIndex = properties.getPayload().getExtractableClasses();
        } else {
            extractableClassToArgumentIndex = Map.of();
        }
    }

    public Optional<Type> extractFrom(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        return getPayloadParameterIndex(method.getParameterTypes(), method.getParameterAnnotations(), methodName)
                .map((parameterPayloadIndex) -> method.getGenericParameterTypes()[parameterPayloadIndex])
                .map(this::extractActualType);
    }

    private Optional<Integer> getPayloadParameterIndex(
            Class<?>[] parameterClasses, Annotation[][] parameterAnnotations, String methodName) {
        return switch (parameterClasses.length) {
            case 0 -> Optional.empty();
            case 1 -> Optional.of(0);
            default -> {
                int payloadAnnotatedParameterIndex = getPayloadAnnotatedParameterIndex(parameterAnnotations);
                if (payloadAnnotatedParameterIndex == -1) {
                    String msg = "Payload cannot be detected. "
                            + " Multi-parameter method must have one parameter annotated with @Payload,"
                            + " but none was found: "
                            + methodName;

                    throw new IllegalArgumentException(msg);
                }
                yield Optional.of(payloadAnnotatedParameterIndex);
            }
        };
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

    private Type extractActualType(Type parameterType) {
        // TODO: add tests / adapt from TypeToClassConverterTest
        Type type = parameterType;

        while (type instanceof ParameterizedType typeParameterized) {
            String typeName = ((ParameterizedType) type).getRawType().getTypeName();
            if (!extractableClassToArgumentIndex.containsKey(typeName)) {
                break;
            }

            Integer index = extractableClassToArgumentIndex.get(typeName);
            type = typeParameterized.getActualTypeArguments()[index];

            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                Type[] lowerBounds = ((WildcardType) type).getLowerBounds();
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
