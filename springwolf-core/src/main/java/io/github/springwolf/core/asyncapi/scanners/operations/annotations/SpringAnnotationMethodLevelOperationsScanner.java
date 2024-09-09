// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.SpringAnnotationMethodLevelScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodParameterService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.operations.OperationsInClassScanner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationMethodLevelOperationsScanner<MethodAnnotation extends Annotation>
        extends SpringAnnotationMethodLevelScanner<MethodAnnotation> implements OperationsInClassScanner {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final List<OperationCustomizer> customizers;
    private final PayloadMethodParameterService payloadMethodParameterService;
    private final HeaderClassExtractor headerClassExtractor;

    public SpringAnnotationMethodLevelOperationsScanner(
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<MethodAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            List<OperationCustomizer> customizers,
            PayloadMethodParameterService payloadMethodParameterService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        super(bindingFactory, asyncHeadersBuilder, componentsService);
        this.customizers = customizers;
        this.methodAnnotationClass = methodAnnotationClass;
        this.payloadMethodParameterService = payloadMethodParameterService;
        this.headerClassExtractor = headerClassExtractor;
    }

    @Override
    public Stream<Map.Entry<String, Operation>> scan(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(AnnotationScannerUtil::isMethodInSourceCode)
                .filter(method -> AnnotationScannerUtil.findAnnotation(methodAnnotationClass, method) != null)
                .map(this::mapMethodToOperation);
    }

    private Map.Entry<String, Operation> mapMethodToOperation(Method method) {
        log.debug("Mapping method \"{}\" to operations", method.getName());

        MethodAnnotation annotation = AnnotationScannerUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        String channelName = bindingFactory.getChannelName(annotation);
        String operationId = StringUtils.joinWith(
                "_", ReferenceUtil.toValidId(channelName), OperationAction.RECEIVE, method.getName());

        PayloadSchemaObject payloadSchema = payloadMethodParameterService.extractSchema(method);
        SchemaObject headerSchema = headerClassExtractor.extractHeader(method, payloadSchema);

        Operation operation = buildOperation(annotation, payloadSchema, headerSchema);
        customizers.forEach(customizer -> customizer.customize(operation, method));
        return Map.entry(operationId, operation);
    }

    private Operation buildOperation(
            MethodAnnotation annotation, PayloadSchemaObject payloadType, SchemaObject headerSchema) {
        MessageObject message = buildMessage(annotation, payloadType, headerSchema);
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(annotation);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        String channelId = ReferenceUtil.toValidId(bindingFactory.getChannelName(annotation));

        return Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(channelId))
                .messages(List.of(MessageReference.toChannelMessage(channelId, message)))
                .bindings(opBinding)
                .build();
    }
}
