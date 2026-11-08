// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import io.swagger.v3.oas.annotations.Hidden;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationScannerUtilTest {

    private static Condition<MethodAndAnnotation<?>> usesMethod(Method method) {
        return new Condition<>(entry -> entry.method().equals(method), "uses method <%s>", method);
    }

    @Nested
    class FindAnnotatedMethodsWithClassAnnotation {
        @Test
        void getRelevantMethods() throws Exception {
            Set<MethodAndAnnotation<MethodAnnotation>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                    ClassWithMethodAnnotation.class, ClassAnnotation.class, MethodAnnotation.class);

            assertThat(methods)
                    .hasSize(1)
                    .haveAtLeastOne(usesMethod(ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod")));
        }

        @Test
        void getAllMethods() throws Exception {
            Set<MethodAndAnnotation<AllMethods>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                    ClassWithMethodAnnotation.class, ClassAnnotation.class, AllMethods.class);

            assertThat(methods)
                    .hasSize(2)
                    .haveAtLeastOne(usesMethod(ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod")))
                    .haveAtLeastOne(usesMethod(ClassWithMethodAnnotation.class.getDeclaredMethod("nonAnnotatedMethod")))
                    .doNotHave(usesMethod(ClassWithMethodAnnotation.class.getDeclaredMethod("hiddenAnnotatedMethod")));
        }

        @Test
        void findNoRelevantMethodsOnClassWithMissingClassAnnotation() {
            Set<MethodAndAnnotation<MethodAnnotation>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                    ClassWithoutClassAnnotation.class, ClassAnnotation.class, MethodAnnotation.class);

            assertThat(methods).isEmpty();
        }

        @Test
        void findNoAllMethodsOnClassWithMissingClassAnnotation() {
            Set<MethodAndAnnotation<AllMethods>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                    ClassWithoutClassAnnotation.class, ClassAnnotation.class, AllMethods.class);

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
            assertThat(AnnotationScannerUtil.isClassRelevant(ClassWithAnnotation.class, ClassAnnotation.class))
                    .isTrue();
        }

        @Test
        void classWithoutAnnotationIsNotRelevant() {
            assertThat(AnnotationScannerUtil.isClassRelevant(ClassWithoutAnnotation.class, ClassAnnotation.class))
                    .isFalse();
        }

        @Test
        void hiddenClassIsNotRelevant() {
            assertThat(AnnotationScannerUtil.isClassRelevant(HiddenTestClass.class, ClassAnnotation.class))
                    .isFalse();
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
        void getRelevantMethods() throws Exception {
            List<MethodAndAnnotation<MethodAnnotation>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithMethodAnnotation.class, MethodAnnotation.class)
                    .toList();

            Method annotatedMethod = ClassWithMethodAnnotation.class.getDeclaredMethod("annotatedMethod");

            assertThat(methods).hasSize(1).contains(new MethodAndAnnotation<>(annotatedMethod, (MethodAnnotation)
                    annotatedMethod.getAnnotations()[0]));
        }

        @Test
        void getAllMethods() throws Exception {
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

        @Test
        void methodsWithTypicalNamesButDifferentSignaturesAreNotIgnored() throws Exception {
            List<MethodAndAnnotation<MethodAnnotation>> methods = AnnotationScannerUtil.findAnnotatedMethods(
                            ClassWithTypicalJavaMethodNames.class, MethodAnnotation.class)
                    .toList();

            Method equalsWithCustomSignature =
                    ClassWithTypicalJavaMethodNames.class.getDeclaredMethod("equals", String.class);
            Method equalsWithObjectSignatureOverride =
                    ClassWithTypicalJavaMethodNames.class.getDeclaredMethod("equals", Object.class);
            Method waitWithCustomSignature =
                    ClassWithTypicalJavaMethodNames.class.getDeclaredMethod("wait", String.class);

            assertThat(methods)
                    .hasSize(3)
                    .contains(new MethodAndAnnotation<>(
                            equalsWithCustomSignature, equalsWithCustomSignature.getAnnotation(MethodAnnotation.class)))
                    .contains(new MethodAndAnnotation<>(
                            waitWithCustomSignature, waitWithCustomSignature.getAnnotation(MethodAnnotation.class)))
                    .contains(new MethodAndAnnotation<>(
                            equalsWithObjectSignatureOverride,
                            equalsWithObjectSignatureOverride.getAnnotation(MethodAnnotation.class)));
        }

        class ClassWithMethodAnnotation {
            @MethodAnnotation
            void annotatedMethod() {}

            void nonAnnotatedMethod() {}

            @MethodAnnotation
            @Hidden
            void hiddenAnnotatedMethod() {}
        }

        class ClassWithTypicalJavaMethodNames {

            @MethodAnnotation
            public void wait(String str) {}

            @MethodAnnotation
            public void equals(String str) {}

            @Override
            @MethodAnnotation
            public boolean equals(Object obj) {
                return super.equals(obj);
            }
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface ClassAnnotation {}

    @Retention(RetentionPolicy.RUNTIME)
    @interface MethodAnnotation {}
}
