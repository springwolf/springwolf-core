package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
public class ReflectionUtils {

    public static <T> Optional<T> getValueOfAnnotationProperty
            (Annotation annotation, String propertyName, Class<T> propertyType) {
        try {
            Object value = annotation.annotationType()
                    .getDeclaredMethod(propertyName)
                    .invoke(annotation, (Object[]) null);

            if (propertyType.isInstance(value)) {
                return Optional.of((T) value);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Failed to read {} value", propertyName, e);
        }

        return Optional.empty();
    }

}
