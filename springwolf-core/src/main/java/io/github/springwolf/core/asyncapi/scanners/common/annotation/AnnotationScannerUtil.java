// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.annotation;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class AnnotationScannerUtil {

    private AnnotationScannerUtil() {}

    /**
     * Find all annotated methods on an annotated class
     * <p>
     * Transform is only called if methods are found
     */
    public static <C extends Annotation, M extends Annotation, R> Stream<R> findAnnotatedMethods(
            Class<?> clazz,
            Class<C> classAnnotationClass,
            Class<M> methodAnnotationClass,
            BiFunction<Class<?>, Set<MethodAndAnnotation<M>>, Stream<R>> transformer) {
        log.debug("Scanning class \"{}\" for @\"{}\" annotation", clazz.getName(), classAnnotationClass.getName());
        Set<MethodAndAnnotation<M>> methods = Stream.of(clazz)
                .filter(it -> AnnotationScannerUtil.isClassRelevant(it, classAnnotationClass))
                .peek(it -> log.debug("Mapping class \"{}\"", it.getName()))
                .flatMap(it -> AnnotationScannerUtil.findAnnotatedMethods(it, methodAnnotationClass))
                .collect(Collectors.toSet());

        if (methods.isEmpty()) {
            return Stream.empty();
        }

        return transformer.apply(clazz, methods);
    }

    static <A extends Annotation> boolean isClassRelevant(Class<?> clazz, Class<A> annotationClass) {
        return AnnotationScannerUtil.isNotHidden(clazz)
                && AnnotationUtil.findFirstAnnotation(annotationClass, clazz) != null;
    }

    public static <A extends Annotation> Stream<MethodAndAnnotation<A>> findAnnotatedMethods(
            Class<?> clazz, Class<A> methodAnnotationClass) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        Stream<Method> methods = Arrays.stream(ReflectionUtils.getAllDeclaredMethods(clazz))
                .filter(ReflectionUtils.USER_DECLARED_METHODS::matches)
                .filter(AnnotationScannerUtil::isNotHidden);

        if (methodAnnotationClass == AllMethods.class) {
            return methods.peek(method -> log.debug("Mapping method \"{}\"", method.getName()))
                    .map(method -> new MethodAndAnnotation<>(method, null));
        }

        return methods.filter(method -> AnnotationUtil.findFirstAnnotation(methodAnnotationClass, method) != null)
                .peek(method -> log.debug("Mapping method \"{}\"", method.getName()))
                .flatMap(method -> AnnotationUtil.findAnnotations(methodAnnotationClass, method).stream()
                        .map(annotation -> new MethodAndAnnotation<>(method, annotation)));
    }

    private static boolean isNotHidden(AnnotatedElement element) {
        return Objects.isNull(AnnotationUtil.findFirstAnnotation(Hidden.class, element));
    }
}
