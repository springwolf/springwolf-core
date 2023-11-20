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
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class SpringPayloadAnnotationTypeExtractor {

    private final Set<String> genericClassesToExtract = Set.of(
            "java.util.List", "org.springframework.messaging.Message", "org.apache.kafka.streams.kstream.KStream");

    public Class<?> getPayloadType(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        Class<?>[] parameterTypes = method.getParameterTypes();
        int parameterPayloadIndex =
                getPayloadParameterIndex(parameterTypes, method.getParameterAnnotations(), methodName);

        return getActualPayloadClass(
                parameterTypes[parameterPayloadIndex], method.getGenericParameterTypes()[parameterPayloadIndex]);
    }

    private Class<?> getActualPayloadClass(Class<?> parameterClass, Type parameterType) {
        String parameterClassName = parameterClass.getName();

        if (genericClassesToExtract.contains(parameterClassName)) {
            try {
                ParameterizedType genericTypeArgument = ((ParameterizedType) parameterType);
                Type actualTypeArgument = genericTypeArgument.getActualTypeArguments()[0];

                String actualTypeName = actualTypeArgument.getTypeName();
                if (actualTypeArgument instanceof ParameterizedType) {
                    actualTypeName = ((ParameterizedType) actualTypeArgument)
                            .getRawType()
                            .getTypeName();
                }

                parameterClass = Class.forName(actualTypeName);
            } catch (Exception ex) {
                log.info("Unable to extract generic data type of %s".formatted(parameterClassName), ex);
            }
        }

        return parameterClass;
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
