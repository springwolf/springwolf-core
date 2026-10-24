// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import jakarta.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Describes where an annotation processed by a {@link BindingFactory} has been found.
 *
 * @param annotatedClass the scanned class, which declares the annotation or the annotated method
 * @param annotatedMethod the annotated method, or null for class-level annotations
 */
public record BindingContext<T extends Annotation>(
        T annotation, Class<?> annotatedClass, @Nullable Method annotatedMethod) {

    public static <U extends Annotation> BindingContext<U> ofAnnotatedClass(Class<?> annotatedClass, U annotation) {
        return new BindingContext<>(annotation, annotatedClass, null);
    }

    public static <U extends Annotation> BindingContext<U> ofAnnotatedMethod(
            Class<?> annotatedClass, Method annotatedMethod, U annotation) {
        return new BindingContext<>(annotation, annotatedClass, annotatedMethod);
    }
}
