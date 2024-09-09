// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.utils;

import jakarta.annotation.Nullable;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Set;

public class AnnotationUtil {
    private AnnotationUtil() {}

    public static <T extends Annotation> T findAnnotationOrThrow(Class<T> annotationClass, AnnotatedElement element) {
        T annotation = findAnnotation(annotationClass, element);
        if (annotation == null) {
            throw new IllegalArgumentException(
                    "Method must be annotated with " + element.getClass().getName());
        }
        return annotation;
    }

    @Nullable
    public static <T extends Annotation> T findAnnotation(Class<T> annotationClass, AnnotatedElement element) {
        Set<T> annotations = findAnnotations(annotationClass, element);

        return annotations.stream().findFirst().orElse(null);
    }

    static <T extends Annotation> Set<T> findAnnotations(Class<T> annotationClass, AnnotatedElement element) {
        return MergedAnnotations.from(
                        element,
                        MergedAnnotations.SearchStrategy.TYPE_HIERARCHY,
                        RepeatableContainers.standardRepeatables())
                .stream(annotationClass)
                .filter(MergedAnnotationPredicates.firstRunOf(MergedAnnotation::getAggregateIndex))
                .collect(MergedAnnotationCollectors.toAnnotationSet());
    }
}
