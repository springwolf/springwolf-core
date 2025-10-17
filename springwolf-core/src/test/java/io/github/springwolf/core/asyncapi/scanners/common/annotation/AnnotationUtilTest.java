// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class AnnotationUtilTest {

    @Nested
    class FindAnnotationOrThrow {
        @Test
        void findNoAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("notAnnotatedMethod");

            // when
            try {
                AnnotationUtil.findFirstAnnotationOrThrow(AnnotationUtilTestAnnotation.class, method);
                fail();
            } catch (IllegalArgumentException e) {
                // then
                assertThat(e.getMessage()).isEqualTo("Method must be annotated with java.lang.reflect.Method");
            }
        }

        @Test
        void findAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethod");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotationOrThrow(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isEqualTo("value");
        }

        @Test
        void findAnnotationRepeatedTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodRepeated");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotationOrThrow(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isEqualTo("value");
        }

        @Test
        void findMetaAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodWithMetaAnnotation");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotationOrThrow(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isEqualTo("metaField");
        }
    }

    @Nested
    class FindAnnotation {
        @Test
        void findNoAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("notAnnotatedMethod");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotation(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation).isNull();
        }

        @Test
        void findAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethod");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotation(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isEqualTo("value");
        }

        @Test
        void findAnnotationRepeatedTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodRepeated");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotation(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isIn("value", "value2");
        }

        @Test
        void findMetaAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodWithMetaAnnotation");

            // when
            AnnotationUtilTestAnnotation annotation =
                    AnnotationUtil.findFirstAnnotation(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation.field()).isEqualTo("metaField");
        }
    }

    @Nested
    class FindAnnotations {
        @Test
        void findNoAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("notAnnotatedMethod");

            // when
            Set<AnnotationUtilTestAnnotation> annotation =
                    AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation).isEmpty();
        }

        @Test
        void findAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethod");

            // when
            Set<AnnotationUtilTestAnnotation> annotation =
                    AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation).hasSize(1);
            assertThat(annotation.stream().findAny().get().field()).isEqualTo("value");
        }

        @Test
        void findAnnotationRepeatedTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodRepeated");

            // when
            Set<AnnotationUtilTestAnnotation> annotation =
                    AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation).hasSize(2);
            assertThat(annotation.stream().map(AnnotationUtilTestAnnotation::field))
                    .containsExactlyInAnyOrder("value", "value2");
        }

        @Test
        void findMetaAnnotationTest() throws Exception {
            Method method = TestClass.class.getMethod("annotatedMethodWithMetaAnnotation");

            // when
            Set<AnnotationUtilTestAnnotation> annotation =
                    AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

            // then
            assertThat(annotation).hasSize(1);
            assertThat(annotation.stream().findAny().get().field()).isEqualTo("metaField");
        }

        @Nested
        class ImplementingInterface {
            @ParameterizedTest
            @ValueSource(classes = {ClassImplementingInterface.class, ClassImplementingInterfaceWithAnnotation.class})
            void scan_componentHasOnlyDeclaredMethods(Class<?> clazz) throws Exception {
                Method method = clazz.getMethod("methodFromInterface", String.class);

                // when
                Set<AnnotationUtilTestAnnotation> annotation =
                        AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

                // then
                assertThat(annotation).hasSize(1);
            }

            private static class ClassImplementingInterface implements ClassInterface<String> {

                @AnnotationUtilTestAnnotation
                @Override
                public void methodFromInterface(String payload) {}
            }

            interface ClassInterface<T> {
                void methodFromInterface(T payload);
            }

            private static class ClassImplementingInterfaceWithAnnotation
                    implements ClassInterfaceWithAnnotation<String> {

                @Override
                public void methodFromInterface(String payload) {}
            }

            interface ClassInterfaceWithAnnotation<T> {
                @AnnotationUtilTestAnnotation
                void methodFromInterface(T payload);
            }
        }

        @Nested
        class AbstractClass {
            @Test
            void scan() throws Exception {
                Method method = ClassExtendsFromAbstractWithListenerAnnotation.class.getMethod(
                        "methodWithAnnotation", String.class);

                // when
                Set<AnnotationUtilTestAnnotation> annotation =
                        AnnotationUtil.findAnnotations(AnnotationUtilTestAnnotation.class, method);

                // then
                assertThat(annotation).hasSize(1);
            }

            private static class ClassExtendsFromAbstractWithListenerAnnotation
                    extends AbstractClassWithListenerAnnotation {}

            private abstract static class AbstractClassWithListenerAnnotation {
                @AnnotationUtilTestAnnotation
                public void methodWithAnnotation(String payload) {}
            }
        }
    }

    private class TestClass {
        public void notAnnotatedMethod() {}

        @AnnotationUtilTestAnnotation
        public void annotatedMethod() {}

        @AnnotationUtilTestAnnotation
        @AnnotationUtilTestAnnotation(field = "value2")
        public void annotatedMethodRepeated() {}

        @AnnotationUtilTestMetaAnnotation
        public void annotatedMethodWithMetaAnnotation() {}
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(AnnotationUtilTestAnnotationRepeatable.class)
    @interface AnnotationUtilTestAnnotation {
        String field() default "value";
    }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface AnnotationUtilTestAnnotationRepeatable {
        AnnotationUtilTestAnnotation[] value();
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @AnnotationUtilTestAnnotation
    @interface AnnotationUtilTestMetaAnnotation {
        @AliasFor(annotation = AnnotationUtilTestAnnotation.class, attribute = "field")
        String metaField() default "metaField";
    }
}
