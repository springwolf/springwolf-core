// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class SpringAnnotationClassLevelChannelsScanner<
                ClassAnnotation extends Annotation, MethodAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final Class<ClassAnnotation> classAnnotationClass;
    private final Class<MethodAnnotation> methodAnnotationClass;
    private final SpringAnnotationMessagesService<ClassAnnotation> springAnnotationMessagesService;
    private final SpringAnnotationChannelService<ClassAnnotation> springAnnotationChannelService;

    @Override
    public List<ChannelObject> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(
                        clazz, classAnnotationClass, methodAnnotationClass, this::mapClassToChannel)
                .toList();
    }

    private Stream<ChannelObject> mapClassToChannel(
            Class<?> component, Set<MethodAndAnnotation<MethodAnnotation>> annotatedMethods) {
        ClassAnnotation classAnnotation = AnnotationUtil.findFirstAnnotationOrThrow(classAnnotationClass, component);
        BindingContext bindingContext = BindingContext.ofAnnotatedClass(component);
        Set<Method> methods =
                annotatedMethods.stream().map(MethodAndAnnotation::method).collect(Collectors.toSet());
        Map<String, Message> messages = new HashMap<>(springAnnotationMessagesService.buildMessages(
                classAnnotation, bindingContext, methods, SpringAnnotationMessagesService.MessageType.CHANNEL));

        return mapClassToChannel(classAnnotation, bindingContext, messages);
    }

    private Stream<ChannelObject> mapClassToChannel(
            ClassAnnotation classAnnotation, BindingContext bindingContext, Map<String, Message> messages) {
        return Stream.of(springAnnotationChannelService.buildChannel(classAnnotation, bindingContext, messages));
    }
}
