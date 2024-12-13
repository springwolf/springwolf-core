// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class SpringAnnotationMethodLevelOperationsScanner<MethodAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final HeaderClassExtractor headerClassExtractor;
    private final PayloadMethodParameterService payloadMethodParameterService;
    private final SpringAnnotationOperationService<MethodAnnotation> springAnnotationOperationService;
    private final List<OperationCustomizer> customizers;

    @Override
    public Stream<Operation> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, methodAnnotationClass)
                .map(this::mapMethodToOperation);
    }

    private Operation mapMethodToOperation(MethodAndAnnotation<MethodAnnotation> method) {
        PayloadSchemaObject payloadSchema = payloadMethodParameterService.extractSchema(method.method());
        SchemaObject headerSchema = headerClassExtractor.extractHeader(method.method(), payloadSchema);

        Operation operation = springAnnotationOperationService.buildOperation(method, payloadSchema, headerSchema);
        customizers.forEach(customizer -> customizer.customize(operation, method.method()));
        return operation;
    }
}
