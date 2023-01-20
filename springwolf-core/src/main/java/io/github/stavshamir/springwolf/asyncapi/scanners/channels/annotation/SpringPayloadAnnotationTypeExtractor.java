package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class SpringPayloadAnnotationTypeExtractor {
    public SpringPayloadAnnotationTypeExtractor() {
    }

    public static Class<?> getPayloadType(Method method) {
        String methodName = String.format("%s::%s", method.getDeclaringClass().getSimpleName(), method.getName());
        log.debug("Finding payload type for {}", methodName);

        Class<?>[] parameterTypes = method.getParameterTypes();
        int parameterPayloadIndex = getPayloadParameterIndex(parameterTypes, method.getParameterAnnotations(), methodName);

        return getPayloadParameterClass(method, parameterTypes, parameterPayloadIndex);
    }

    static Class<?> getPayloadParameterClass(Method method, Class<?>[] parameterTypes, int parameterPayloadIndex) {
        Class<?> parameterClass = parameterTypes[parameterPayloadIndex];

        try {
            // Resolve generic type for batch listeners
            if (parameterClass == List.class) {
                Type type = ((ParameterizedType) method.getGenericParameterTypes()[parameterPayloadIndex]).getActualTypeArguments()[0];
                return Class.forName(type.getTypeName());
            }
        } catch (Exception ex) {
            log.info("Found payload type List<?>, but was unable to extract generic data type", ex);
        }

        return parameterClass;
    }

    static int getPayloadParameterIndex(Class<?>[] parameterTypes, Annotation[][] parameterAnnotations, String methodName) {
        switch (parameterTypes.length) {
            case 0:
                throw new IllegalArgumentException("Listener methods must not have 0 parameters: " + methodName);
            case 1:
                return 0;
            default:
                int payloadAnnotatedParameterIndex = getPayloadAnnotatedParameterIndex(parameterAnnotations);

                if (payloadAnnotatedParameterIndex == -1) {
                    String msg = "Multi-parameter KafkaListener methods must have one parameter annotated with @Payload, "
                            + "but none was found: "
                            + methodName;

                    throw new IllegalArgumentException(msg);
                }

                return payloadAnnotatedParameterIndex;
        }
    }

    static int getPayloadAnnotatedParameterIndex(Annotation[][] parameterAnnotations) {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            boolean hasPayloadAnnotation = Arrays.stream(annotations)
                    .anyMatch(annotation -> annotation instanceof Payload);

            if (hasPayloadAnnotation) {
                return i;
            }
        }

        return -1;
    }
}