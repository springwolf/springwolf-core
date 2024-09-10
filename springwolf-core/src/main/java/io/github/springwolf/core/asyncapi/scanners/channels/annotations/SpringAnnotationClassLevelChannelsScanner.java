// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationClassLevelChannelsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final Class<ClassAnnotation> classAnnotationClass;
    private final Class<MethodAnnotation> methodAnnotationClass;
    private final BindingFactory<ClassAnnotation> bindingFactory;
    private final SpringAnnotationMessagesService<ClassAnnotation> springAnnotationMessagesService;
    private final SpringAnnotationChannelService<ClassAnnotation> springAnnotationChannelService;

    public SpringAnnotationClassLevelChannelsScanner(
            Class<ClassAnnotation> classAnnotationClass,
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<ClassAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadMethodService payloadMethodService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        this.classAnnotationClass = classAnnotationClass;
        this.methodAnnotationClass = methodAnnotationClass;
        this.bindingFactory = bindingFactory;
        this.springAnnotationMessagesService = new SpringAnnotationMessagesService<>(
                bindingFactory, asyncHeadersBuilder, payloadMethodService, headerClassExtractor, componentsService);
        this.springAnnotationChannelService = new SpringAnnotationChannelService<>(bindingFactory);
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(
                clazz, classAnnotationClass, methodAnnotationClass, this::mapClassToChannel);
    }

    private Stream<Map.Entry<String, ChannelObject>> mapClassToChannel(
            Class<?> component, Set<Method> annotatedMethods) {
        ClassAnnotation classAnnotation = AnnotationUtil.findAnnotationOrThrow(classAnnotationClass, component);
        Map<String, Message> messages = new HashMap<>(springAnnotationMessagesService.buildMessages(
                classAnnotation, annotatedMethods, SpringAnnotationMessagesService.MessageType.CHANNEL));

        ChannelObject channelItem = springAnnotationChannelService.buildChannel(classAnnotation, messages);
        return Stream.of(Map.entry(channelItem.getChannelId(), channelItem));
    }
}
