// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.common;

import java.lang.reflect.Method;

public record BindingContext(Class<?> annotatedClass, Method annotatedMethod) {
    public BindingContext {
        if (annotatedClass == null && annotatedMethod == null) {
            throw new IllegalArgumentException("Either annotatedClass or annotatedMethod must be non-null");
        }
    }

    public Class<?> getClassContext() {
        if (annotatedClass != null) {
            return annotatedClass;
        }
        if (annotatedMethod != null) {
            return annotatedMethod.getDeclaringClass();
        }

        throw new IllegalStateException("Either annotatedClass or annotatedMethod must be non-null");
    }

    public static BindingContext ofAnnotatedMethod(Method annotatedMethod) {
        return new BindingContext(null, annotatedMethod);
    }

    public static BindingContext ofAnnotatedClass(Class<?> annotatedClass) {
        return new BindingContext(annotatedClass, null);
    }
}
