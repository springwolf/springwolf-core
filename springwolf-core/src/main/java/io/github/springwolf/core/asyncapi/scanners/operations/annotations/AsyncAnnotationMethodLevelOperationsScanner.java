// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class AsyncAnnotationMethodLevelOperationsScanner<MethodAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final StringValueResolverProxy resolver;
    private final AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationOperationService<MethodAnnotation> asyncAnnotationOperationService;
    private final List<OperationCustomizer> customizers;

    public AsyncAnnotationMethodLevelOperationsScanner(
            AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider,
            ComponentsService componentsService,
            PayloadAsyncOperationService payloadAsyncOperationService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            List<OperationCustomizer> customizers,
            StringValueResolverProxy resolver) {
        this.resolver = resolver;
        this.asyncAnnotationProvider = asyncAnnotationProvider;
        this.asyncAnnotationOperationService = new AsyncAnnotationOperationService<>(
                asyncAnnotationProvider,
                operationBindingProcessors,
                resolver,
                new AsyncAnnotationMessageService(
                        payloadAsyncOperationService, componentsService, messageBindingProcessors, resolver));
        this.customizers = customizers;
    }

    @Override
    public Stream<Map.Entry<String, Operation>> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, this.asyncAnnotationProvider.getAnnotation())
                .map(this::mapMethodToOperation);
    }

    private Map.Entry<String, Operation> mapMethodToOperation(
            MethodAndAnnotation<MethodAnnotation> methodAndAnnotation) {
        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());

        String channelName = resolver.resolveStringValue(operationAnnotation.channelName());
        String channelId = ReferenceUtil.toValidId(channelName);
        // operationId should be part of the buildOperation method and part of Operation object
        String operationId = StringUtils.joinWith(
                "_",
                channelId,
                this.asyncAnnotationProvider.getOperationType().type,
                methodAndAnnotation.method().getName());

        Operation operation = asyncAnnotationOperationService.buildOperation(
                operationAnnotation, methodAndAnnotation.method(), channelId);
        customizers.forEach(customizer -> customizer.customize(operation, methodAndAnnotation.method()));
        return Map.entry(operationId, operation);
    }
}
