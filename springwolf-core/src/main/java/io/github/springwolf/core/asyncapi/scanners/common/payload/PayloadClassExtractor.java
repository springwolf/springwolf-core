// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.payload;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class PayloadClassExtractor {
    private final TypeToClassConverter typeToClassConverter;

    public Optional<Class<?>> extractFrom(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        return getPayloadParameterIndex(method.getParameterTypes(), method.getParameterAnnotations(), methodName)
                .map((parameterPayloadIndex) ->
                        typeToClassConverter.extractClass(method.getGenericParameterTypes()[parameterPayloadIndex]));
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
}
