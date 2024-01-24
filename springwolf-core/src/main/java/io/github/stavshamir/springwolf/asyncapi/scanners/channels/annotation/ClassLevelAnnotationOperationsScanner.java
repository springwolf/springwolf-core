// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleOperationsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersBuilder;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.OperationAction;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class ClassLevelAnnotationOperationsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        extends ClassLevelAnnotationScanner<ClassAnnotation, MethodAnnotation>
        implements SimpleOperationsScanner.ClassProcessor {

    public ClassLevelAnnotationOperationsScanner(
            Class<ClassAnnotation> classAnnotationClass,
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<ClassAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadClassExtractor payloadClassExtractor,
            SchemasService schemasService) {
        super(
                classAnnotationClass,
                methodAnnotationClass,
                bindingFactory,
                asyncHeadersBuilder,
                payloadClassExtractor,
                schemasService);
    }

    @Override
    public Stream<Map.Entry<String, Operation>> process(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods", clazz.getName(), classAnnotationClass.getName());

        return Stream.of(clazz).filter(this::isClassAnnotated).flatMap(this::mapClassToOperation);
    }

    private Stream<Map.Entry<String, Operation>> mapClassToOperation(Class<?> component) {
        log.debug("Mapping class \"{}\" to operations", component.getName());

        ClassAnnotation classAnnotation = AnnotationUtil.findAnnotationOrThrow(classAnnotationClass, component);

        Set<Method> annotatedMethods = getAnnotatedMethods(component);
        if (annotatedMethods.isEmpty()) {
            return Stream.empty();
        }

        String channelName = bindingFactory.getChannelName(classAnnotation);
        String operationId = channelName + "_" + OperationAction.RECEIVE + "_" + component.getSimpleName();

        Operation operation = buildOperation(classAnnotation, annotatedMethods);

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
                .channel(ChannelReference.fromChannel(channelName))
                .messages(messages.values().stream().toList())
                .bindings(opBinding)
                .build();
    }
}
