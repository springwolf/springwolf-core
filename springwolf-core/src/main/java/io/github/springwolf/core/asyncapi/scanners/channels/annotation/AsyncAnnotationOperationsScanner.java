// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotation;

import io.github.springwolf.core.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.OperationMerger;
import io.github.springwolf.core.asyncapi.scanners.channels.OperationsScanner;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.classes.ClassScanner;
import io.github.springwolf.core.schemas.ComponentsService;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

@Slf4j
public class AsyncAnnotationOperationsScanner<A extends Annotation> extends AsyncAnnotationScanner<A>
        implements OperationsScanner {

    private final ClassScanner classScanner;

    public AsyncAnnotationOperationsScanner(
            AsyncAnnotationProvider<A> asyncAnnotationProvider,
            ClassScanner classScanner,
            ComponentsService componentsService,
            PayloadClassExtractor payloadClassExtractor,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors) {
        super(
                asyncAnnotationProvider,
                payloadClassExtractor,
                componentsService,
                operationBindingProcessors,
                messageBindingProcessors);
        this.classScanner = classScanner;
    }

    @Override
    public Map<String, Operation> scan() {
        List<Map.Entry<String, Operation>> operations = classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .map(this::buildOperation)
                .toList();

        return OperationMerger.mergeOperations(operations);
    }

    private Map.Entry<String, Operation> buildOperation(MethodAndAnnotation<A> methodAndAnnotation) {
        AsyncOperation operationAnnotation =
                this.asyncAnnotationProvider.getAsyncOperation(methodAndAnnotation.annotation());
        String channelName = resolver.resolveStringValue(operationAnnotation.channelName());
        String operationId = channelName + "_" + this.asyncAnnotationProvider.getOperationType().type + "_"
                + methodAndAnnotation.method().getName();

        Operation operation = buildOperation(operationAnnotation, methodAndAnnotation.method(), channelName);
        operation.setAction(this.asyncAnnotationProvider.getOperationType());

        return Map.entry(operationId, operation);
    }
}
