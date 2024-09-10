// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import io.swagger.v3.oas.annotations.Hidden;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnnotationScannerUtilTest {

    @Nested
    class FindAnnotatedMethodsWithClassAnnotation {
        @Test
        void getRelevantMethods() throws NoSuchMethodException {
            List<Method> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithMethodAnnotation.class,
                            ClassAnnotation.class,
                            MethodAnnotation.class,
                            (c, m) -> m.stream())
                    .toList();

            assertThat(methods)
                    .hasSize(1)
                    .contains(ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod"));
        }

        @Test
        void getAllMethods() throws NoSuchMethodException {
            List<Method> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithMethodAnnotation.class,
                            ClassAnnotation.class,
                            AllMethods.class,
                            (c, m) -> m.stream())
                    .toList();

            assertThat(methods)
                    .hasSize(2)
                    .contains(ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod"))
                    .contains(ClassWithMethodAnnotation.class.getDeclaredMethod("nonAnnotatedMethod"))
                    .doesNotContain(ClassWithMethodAnnotation.class.getDeclaredMethod("hiddenAnnotatedMethod"));
        }

        @Test
        void findNoRelevantMethodsOnClassWithMissingClassAnnotation() throws NoSuchMethodException {
            List<Method> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithoutClassAnnotation.class,
                            ClassAnnotation.class,
                            MethodAnnotation.class,
                            (c, m) -> m.stream())
                    .toList();

            assertThat(methods).isEmpty();
        }

        @Test
        void findNoAllMethodsOnClassWithMissingClassAnnotation() throws NoSuchMethodException {
            List<Method> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithoutClassAnnotation.class,
                            ClassAnnotation.class,
                            AllMethods.class,
                            (c, m) -> m.stream())
                    .toList();

            assertThat(methods).isEmpty();
        }

        @ClassAnnotation
        class ClassWithMethodAnnotation {
            @MethodAnnotation
            void annotatedMethod() {}

            void nonAnnotatedMethod() {}

            @MethodAnnotation
            @Hidden
            void hiddenAnnotatedMethod() {}
        }

        class ClassWithoutClassAnnotation {
            @MethodAnnotation
            void annotatedMethod() {}

            void nonAnnotatedMethod() {}

            @MethodAnnotation
            @Hidden
            void hiddenAnnotatedMethod() {}
        }
    }

    @Nested
    class IsClassRelevant {
        @Test
        void classWithAnnotationIsRelevant() {
            assertTrue(AnnotationScannerUtil.isClassRelevant(ClassWithAnnotation.class, ClassAnnotation.class));
        }

        @Test
        void classWithoutAnnotationIsNotRelevant() {
            assertFalse(AnnotationScannerUtil.isClassRelevant(ClassWithoutAnnotation.class, ClassAnnotation.class));
        }

        @Test
        void hiddenClassIsNotRelevant() {
            assertFalse(AnnotationScannerUtil.isClassRelevant(HiddenTestClass.class, ClassAnnotation.class));
        }

        @ClassAnnotation
        class ClassWithAnnotation {}

        class ClassWithoutAnnotation {}

        @ClassAnnotation
        @Hidden
        class HiddenTestClass {}
    }

    @Nested
    class FindAnnotatedMethods {

        @Test
        void getRelevantMethods() throws NoSuchMethodException {
            List<MethodAndAnnotation<MethodAnnotation>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithMethodAnnotation.class, MethodAnnotation.class)
                    .toList();

            Method annotatedMethod = ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod");

            assertThat(methods).hasSize(1).contains(new MethodAndAnnotation<>(annotatedMethod, (MethodAnnotation)
                    annotatedMethod.getAnnotations()[0]));
        }

        @Test
        void getAllMethods() throws NoSuchMethodException {
            List<MethodAndAnnotation<AllMethods>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithMethodAnnotation.class, AllMethods.class)
                    .toList();

            assertThat(methods)
                    .hasSize(2)
                    .contains(new MethodAndAnnotation<>(
                            ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod"), null))
                    .contains(new MethodAndAnnotation<>(
                            ClassWithMethodAnnotation.class.getDeclaredMethod("nonAnnotatedMethod"), null))
                    .doesNotContain(new MethodAndAnnotation<>(
                            ClassWithMethodAnnotation.class.getDeclaredMethod("hiddenAnnotatedMethod"), null));
        }

        class ClassWithMethodAnnotation {
            @MethodAnnotation
            void annotatedMethod() {}

            void nonAnnotatedMethod() {}

            @MethodAnnotation
            @Hidden
            void hiddenAnnotatedMethod() {}
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClassAnnotation {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnnotation {}
}
