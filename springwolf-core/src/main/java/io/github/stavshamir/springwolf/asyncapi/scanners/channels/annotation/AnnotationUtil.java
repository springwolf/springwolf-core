// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotationCollectors;
import org.springframework.core.annotation.MergedAnnotationPredicates;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.RepeatableContainers;
import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Set;

public class AnnotationUtil {

    public static <T extends Annotation> T findAnnotationOrThrow(AnnotatedElement element, Class<T> annotationClass) {
        T annotation = findAnnotation(element, annotationClass);
        if (annotation == null) {
            throw new IllegalArgumentException(
                    "Method must be annotated with " + element.getClass().getName());
        }
        return annotation;
    }

    @Nullable
    public static <T extends Annotation> T findAnnotation(AnnotatedElement element, Class<T> annotationClass) {
        Set<T> annotations = MergedAnnotations.from(
                        element,
                        MergedAnnotations.SearchStrategy.TYPE_HIERARCHY,
                        RepeatableContainers.standardRepeatables())
                .stream(annotationClass)
                .filter(MergedAnnotationPredicates.firstRunOf(MergedAnnotation::getAggregateIndex))
                .map(MergedAnnotation::withNonMergedAttributes)
                .collect(MergedAnnotationCollectors.toAnnotationSet());

        return annotations.stream().findFirst().orElse(null);
    }
}
