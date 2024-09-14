// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AllMethods;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationClassLevelOperationsScanner<ClassAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final Class<ClassAnnotation> classAnnotationClass;
    private final AsyncAnnotationProvider<ClassAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationOperationService<ClassAnnotation> asyncAnnotationOperationsService;
    private final List<OperationCustomizer> customizers;

    @Override
    public Stream<Map.Entry<String, Operation>> scan(Class<?> clazz) {
        Set<MethodAndAnnotation<ClassAnnotation>> methodAndAnnotation = AnnotationScannerUtil.findAnnotatedMethods(
                        clazz, classAnnotationClass, AllMethods.class, (cl, m) -> {
                            ClassAnnotation classAnnotation =
                                    AnnotationUtil.findFirstAnnotation(classAnnotationClass, clazz);
                            return m.stream()
                                    .map(method -> new MethodAndAnnotation<>(method.method(), classAnnotation));
                        })
                .collect(Collectors.toSet());
        if (methodAndAnnotation.isEmpty()) {
            return Stream.empty();
        }

        return mapClassToOperation(clazz, methodAndAnnotation);
    }

    private Stream<Map.Entry<String, Operation>> mapClassToOperation(
            Class<?> component, Set<MethodAndAnnotation<ClassAnnotation>> annotatedMethods) {
        ClassAnnotation classAnnotation = AnnotationUtil.findFirstAnnotationOrThrow(classAnnotationClass, component);
        AsyncOperation asyncOperation = asyncAnnotationProvider.getAsyncOperation(classAnnotation);

        String channelName =
                asyncAnnotationProvider.getAsyncOperation(classAnnotation).channelName();
        String channelId = ReferenceUtil.toValidId(channelName);
        String operationId = StringUtils.joinWith(
                "_", channelId, asyncAnnotationProvider.getOperationType().type, component.getSimpleName());

        Set<Method> methods =
                annotatedMethods.stream().map(MethodAndAnnotation::method).collect(Collectors.toSet());
        Operation operation = asyncAnnotationOperationsService.buildOperation(asyncOperation, methods, channelId);
        annotatedMethods.forEach(
                method -> customizers.forEach(customizer -> customizer.customize(operation, method.method())));
        return Stream.of(Map.entry(operationId, operation));
    }
}
