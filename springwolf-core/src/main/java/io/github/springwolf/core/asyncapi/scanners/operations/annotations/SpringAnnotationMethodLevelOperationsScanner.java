// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.SpringAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationMethodLevelOperationsScanner<MethodAnnotation extends Annotation>
        implements OperationsInClassScanner {

    private final BindingFactory<MethodAnnotation> bindingFactory;
    private final Class<MethodAnnotation> methodAnnotationClass;
    private final List<OperationCustomizer> customizers;
    private final PayloadMethodParameterService payloadMethodParameterService;
    private final HeaderClassExtractor headerClassExtractor;
    private final SpringAnnotationOperationService<MethodAnnotation> springAnnotationOperationService;

    public SpringAnnotationMethodLevelOperationsScanner(
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<MethodAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            List<OperationCustomizer> customizers,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        this.bindingFactory = bindingFactory;
        this.customizers = customizers;
        this.methodAnnotationClass = methodAnnotationClass;
        this.payloadMethodParameterService = payloadMethodParameterService;
        this.headerClassExtractor = headerClassExtractor;
        this.springAnnotationOperationService = new SpringAnnotationOperationService<>(
                bindingFactory,
                new SpringAnnotationMessageService<>(bindingFactory, asyncHeadersBuilder, componentsService));
    }

    @Override
    public Stream<Map.Entry<String, Operation>> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, methodAnnotationClass)
                .map(this::mapMethodToOperation);
    }

    private Map.Entry<String, Operation> mapMethodToOperation(
            AnnotationScannerUtil.MethodAndAnnotation<MethodAnnotation> method) {
        MethodAnnotation annotation = AnnotationUtil.findAnnotationOrThrow(methodAnnotationClass, method.method());

        String channelName = bindingFactory.getChannelName(annotation);
        String channelId = ReferenceUtil.toValidId(channelName);
        String operationId = StringUtils.joinWith(
                "_", channelId, OperationAction.RECEIVE, method.method().getName());

        PayloadSchemaObject payloadSchema = payloadMethodParameterService.extractSchema(method.method());
        SchemaObject headerSchema = headerClassExtractor.extractHeader(method.method(), payloadSchema);

        Operation operation = springAnnotationOperationService.buildOperation(annotation, payloadSchema, headerSchema);
        customizers.forEach(customizer -> customizer.customize(operation, method.method()));
        return Map.entry(operationId, operation);
    }
}
