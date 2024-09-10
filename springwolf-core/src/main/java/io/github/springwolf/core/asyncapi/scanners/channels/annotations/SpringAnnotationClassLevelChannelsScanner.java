// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.SpringAnnotationClassLevelAnnotationScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
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
        extends SpringAnnotationClassLevelAnnotationScanner<ClassAnnotation, MethodAnnotation>
        implements ChannelsInClassScanner {

    public SpringAnnotationClassLevelChannelsScanner(
            Class<ClassAnnotation> classAnnotationClass,
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<ClassAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadMethodService payloadMethodService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        super(
                classAnnotationClass,
                methodAnnotationClass,
                bindingFactory,
                asyncHeadersBuilder,
                payloadMethodService,
                headerClassExtractor,
                componentsService);
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(
                clazz, classAnnotationClass, methodAnnotationClass, this::mapClassToChannel);
    }

    private Stream<Map.Entry<String, ChannelObject>> mapClassToChannel(
            Class<?> component, Set<Method> annotatedMethods) {
        ClassAnnotation classAnnotation = AnnotationUtil.findAnnotationOrThrow(classAnnotationClass, component);
        ChannelObject channelItem = buildChannel(classAnnotation, annotatedMethods);
        return Stream.of(Map.entry(channelItem.getChannelId(), channelItem));
    }

    private ChannelObject buildChannel(ClassAnnotation classAnnotation, Set<Method> methods) {
        var messages = buildMessages(classAnnotation, methods, MessageType.CHANNEL);
        return buildChannel(classAnnotation, messages);
    }

    private ChannelObject buildChannel(ClassAnnotation classAnnotation, Map<String, MessageReference> messages) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(classAnnotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        String channelName = bindingFactory.getChannelName(classAnnotation);
        String channelId = ReferenceUtil.toValidId(channelName);

        return ChannelObject.builder()
                .channelId(channelId)
                .address(channelName)
                .bindings(chBinding)
                .messages(new HashMap<>(messages))
                .build();
    }
}
