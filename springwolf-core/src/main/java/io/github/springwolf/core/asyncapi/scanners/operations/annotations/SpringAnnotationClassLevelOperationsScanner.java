// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.operations.annotations;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.ClassLevelAnnotationScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationClassLevelOperationsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        extends ClassLevelAnnotationScanner<ClassAnnotation, MethodAnnotation>
        implements SpringAnnotationOperationsScannerDelegator {

    private final List<OperationCustomizer> customizers;

    public SpringAnnotationClassLevelOperationsScanner(
            Class<ClassAnnotation> classAnnotationClass,
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<ClassAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadMethodService payloadMethodService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService,
            List<OperationCustomizer> customizers) {
        super(
                classAnnotationClass,
                methodAnnotationClass,
                bindingFactory,
                asyncHeadersBuilder,
                payloadMethodService,
                headerClassExtractor,
                componentsService);
        this.customizers = customizers;
    }

    @Override
    public Stream<Map.Entry<String, Operation>> scan(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods", clazz.getName(), classAnnotationClass.getName());

        return Stream.of(clazz).filter(this::isClassAnnotated).flatMap(this::mapClassToOperation);
    }

    private Stream<Map.Entry<String, Operation>> mapClassToOperation(Class<?> component) {
        log.debug("Mapping class \"{}\" to operations", component.getName());

        ClassAnnotation classAnnotation = AnnotationScannerUtil.findAnnotationOrThrow(classAnnotationClass, component);

        Set<Method> annotatedMethods = getAnnotatedMethods(component);
        if (annotatedMethods.isEmpty()) {
            return Stream.empty();
        }

        String channelName = bindingFactory.getChannelName(classAnnotation);
        String operationId = StringUtils.joinWith(
                "_", ReferenceUtil.toValidId(channelName), OperationAction.RECEIVE, component.getSimpleName());

        Operation operation = buildOperation(classAnnotation, annotatedMethods);
        annotatedMethods.forEach(method -> customizers.forEach(customizer -> customizer.customize(operation, method)));

        return Stream.of(Map.entry(operationId, operation));
    }

    private Operation buildOperation(ClassAnnotation classAnnotation, Set<Method> methods) {
        var messages = buildMessages(classAnnotation, methods, MessageType.OPERATION);
        return buildOperation(classAnnotation, messages);
    }

    private Operation buildOperation(ClassAnnotation classAnnotation, Map<String, MessageReference> messages) {
        Map<String, OperationBinding> operationBinding = bindingFactory.buildOperationBinding(classAnnotation);
        Map<String, OperationBinding> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;
        String channelName = bindingFactory.getChannelName(classAnnotation);

        return Operation.builder()
                .action(OperationAction.RECEIVE)
                .channel(ChannelReference.fromChannel(ReferenceUtil.toValidId(channelName)))
                .messages(messages.values().stream().toList())
                .bindings(opBinding)
                .build();
    }
}
