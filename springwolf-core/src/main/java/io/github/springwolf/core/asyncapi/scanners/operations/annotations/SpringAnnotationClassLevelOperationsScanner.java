// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationsService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class SpringAnnotationClassLevelOperationsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final Class<ClassAnnotation> classAnnotationClass;
    private final Class<MethodAnnotation> methodAnnotationClass;
    private final SpringAnnotationOperationsService<ClassAnnotation> springAnnotationOperationsService;
    private final List<OperationCustomizer> customizers;

    @Override
    public Stream<Operation> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(
                clazz, classAnnotationClass, methodAnnotationClass, this::mapClassToOperation);
    }

    private Stream<Operation> mapClassToOperation(
            Class<?> component, Set<MethodAndAnnotation<MethodAnnotation>> annotatedMethods) {
        ClassAnnotation classAnnotation = AnnotationUtil.findFirstAnnotationOrThrow(classAnnotationClass, component);

        Set<Method> methods =
                annotatedMethods.stream().map(MethodAndAnnotation::method).collect(Collectors.toSet());
        Operation operation = springAnnotationOperationsService.buildOperation(classAnnotation, component, methods);
        annotatedMethods.forEach(
                method -> customizers.forEach(customizer -> customizer.customize(operation, method.method())));
        return Stream.of(operation);
    }
}
