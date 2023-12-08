// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2._6_0.model.channel.ChannelItem;
import com.asyncapi.v2._6_0.model.channel.operation.Operation;
import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersBuilder;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.HeaderReference;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;
import static java.util.stream.Collectors.toSet;

@RequiredArgsConstructor
@Slf4j
public class ClassLevelAnnotationChannelsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        implements SimpleChannelsScanner.ClassProcessor {

    private final Class<ClassAnnotation> classAnnotationClass;
    private final Class<MethodAnnotation> methodAnnotationClass;
    private final BindingFactory<ClassAnnotation> bindingFactory;
    private final AsyncHeadersBuilder asyncHeadersBuilder;
    private final PayloadClassExtractor payloadClassExtractor;
    private final SchemasService schemasService;

    @Override
    public Stream<Map.Entry<String, ChannelItem>> process(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods", clazz.getName(), classAnnotationClass.getName());

        return Stream.of(clazz).filter(this::isClassAnnotated).flatMap(this::mapClassToChannel);
    }

    private boolean isClassAnnotated(Class<?> component) {
        return AnnotationUtil.findAnnotation(classAnnotationClass, component) != null;
    }

    private Set<Method> getAnnotatedMethods(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtils.findAnnotation(method, methodAnnotationClass) != null)
                .collect(toSet());
    }

    private Stream<Map.Entry<String, ChannelItem>> mapClassToChannel(Class<?> component) {
        log.debug("Mapping class \"{}\" to channels", component.getName());

        ClassAnnotation classAnnotation = AnnotationUtil.findAnnotationOrThrow(classAnnotationClass, component);

        Set<Method> annotatedMethods = getAnnotatedMethods(component);
        if (annotatedMethods.isEmpty()) {
            return Stream.empty();
        }

        String channelName = bindingFactory.getChannelName(classAnnotation);
        String operationId = channelName + "_publish_" + component.getSimpleName();

        ChannelItem channelItem = buildChannel(classAnnotation, operationId, annotatedMethods);

        return Stream.of(Map.entry(channelName, channelItem));
    }

    private ChannelItem buildChannel(ClassAnnotation classAnnotation, String operationId, Set<Method> methods) {
        Object message = buildMessageObject(classAnnotation, methods);
        Operation operation = buildOperation(classAnnotation, operationId, message);
        return buildChannelItem(classAnnotation, operation);
    }

    private Object buildMessageObject(ClassAnnotation classAnnotation, Set<Method> methods) {
        Set<Message> messages = methods.stream()
                .map((Method method) -> {
                    Class<?> payloadType = payloadClassExtractor.extractFrom(method);
                    return buildMessage(classAnnotation, payloadType);
                })
                .collect(toSet());

        return toMessageObjectOrComposition(messages);
    }

    private Message buildMessage(ClassAnnotation classAnnotation, Class<?> payloadType) {
        Map<String, ? extends MessageBinding> messageBinding = bindingFactory.buildMessageBinding(classAnnotation);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(asyncHeadersBuilder.buildHeaders(payloadType));

        return Message.builder()
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(PayloadReference.fromModelName(modelName))
                .headers(HeaderReference.fromModelName(headerModelName))
                .bindings(messageBinding)
                .build();
    }

    private Operation buildOperation(ClassAnnotation classAnnotation, String operationId, Object message) {
        Map<String, ? extends OperationBinding> operationBinding =
                bindingFactory.buildOperationBinding(classAnnotation);
        Map<String, Object> opBinding = operationBinding != null ? new HashMap<>(operationBinding) : null;

        return Operation.builder()
                .description("Auto-generated description")
                .operationId(operationId)
                .message(message)
                .bindings(opBinding)
                .build();
    }

    private ChannelItem buildChannelItem(ClassAnnotation classAnnotation, Operation operation) {
        Map<String, ? extends ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(classAnnotation);
        Map<String, Object> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        return ChannelItem.builder().bindings(chBinding).publish(operation).build();
    }
}
