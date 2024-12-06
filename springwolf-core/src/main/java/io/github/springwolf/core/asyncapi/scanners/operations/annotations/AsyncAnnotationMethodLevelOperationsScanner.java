// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationMethodLevelOperationsScanner<MethodAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationOperationService<MethodAnnotation> asyncAnnotationOperationService;
    private final List<OperationCustomizer> customizers;

    @Override
    public Stream<Operation> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, this.asyncAnnotationProvider.getAnnotation())
                .map(this::mapMethodToOperation);
    }

    private Operation mapMethodToOperation(MethodAndAnnotation<MethodAnnotation> methodAndAnnotation) {
        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());

        Operation operation = asyncAnnotationOperationService.buildOperation(
                operationAnnotation, Set.of(methodAndAnnotation.method()));
        customizers.forEach(customizer -> customizer.customize(operation, methodAndAnnotation.method()));
        return operation;
    }
}
