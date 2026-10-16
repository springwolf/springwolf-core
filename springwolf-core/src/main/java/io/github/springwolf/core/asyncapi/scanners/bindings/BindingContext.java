// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import jakarta.annotation.Nullable;

import java.lang.reflect.Method;

/**
 * Describes where an annotation processed by a {@link BindingFactory} has been found.
 *
 * @param annotatedClass the scanned class, which declares the annotation or the annotated method
 * @param annotatedMethod the annotated method, or null for class-level annotations
 */
public record BindingContext(
        Class<?> annotatedClass, @Nullable Method annotatedMethod) {

    public static BindingContext ofAnnotatedClass(Class<?> annotatedClass) {
        return new BindingContext(annotatedClass, null);
    }

    public static BindingContext ofAnnotatedMethod(Class<?> annotatedClass, Method annotatedMethod) {
        return new BindingContext(annotatedClass, annotatedMethod);
    }
}
