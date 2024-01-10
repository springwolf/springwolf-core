// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.BindingFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.SimpleChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersBuilder;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageHeaders;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessagePayload;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.MultiFormatSchema;
import io.github.stavshamir.springwolf.asyncapi.v3.model.schema.SchemaReference;
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

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessagesMap;
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
    public Stream<Map.Entry<String, ChannelObject>> process(Class<?> clazz) {
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

    private Stream<Map.Entry<String, ChannelObject>> mapClassToChannel(Class<?> component) {
        log.debug("Mapping class \"{}\" to channels", component.getName());

        ClassAnnotation classAnnotation = AnnotationUtil.findAnnotationOrThrow(classAnnotationClass, component);

        Set<Method> annotatedMethods = getAnnotatedMethods(component);
        if (annotatedMethods.isEmpty()) {
            return Stream.empty();
        }

        String channelName = bindingFactory.getChannelName(classAnnotation);

        ChannelObject channelItem = buildChannelItem(classAnnotation, annotatedMethods);

        return Stream.of(Map.entry(channelName, channelItem));
    }

    private ChannelObject buildChannelItem(ClassAnnotation classAnnotation, Set<Method> methods) {
        var messages = buildMessages(classAnnotation, methods);
        return buildChannelItem(classAnnotation, messages);
    }

    private Map<String, Message> buildMessages(ClassAnnotation classAnnotation, Set<Method> methods) {
        Set<MessageObject> messages = methods.stream()
                .map((Method method) -> {
                    Class<?> payloadType = payloadClassExtractor.extractFrom(method);
                    return buildMessage(classAnnotation, payloadType);
                })
                .collect(toSet());

        return toMessagesMap(messages);
    }

    private MessageObject buildMessage(ClassAnnotation classAnnotation, Class<?> payloadType) {
        Map<String, MessageBinding> messageBinding = bindingFactory.buildMessageBinding(classAnnotation);
        String modelName = schemasService.register(payloadType);
        String headerModelName = schemasService.register(asyncHeadersBuilder.buildHeaders(payloadType));

        MessagePayload payload = MessagePayload.of(MultiFormatSchema.builder()
                .schema(SchemaReference.fromSchema(modelName))
                .build());

        return MessageObject.builder()
                .messageId(payloadType.getName())
                .name(payloadType.getName())
                .title(payloadType.getSimpleName())
                .description(null)
                .payload(payload)
                .headers(MessageHeaders.of(MessageReference.fromSchema(headerModelName)))
                .bindings(messageBinding)
                .build();
    }

    private ChannelObject buildChannelItem(ClassAnnotation classAnnotation, Map<String, Message> messages) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(classAnnotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        return ChannelObject.builder().bindings(chBinding).messages(messages).build();
    }
}
