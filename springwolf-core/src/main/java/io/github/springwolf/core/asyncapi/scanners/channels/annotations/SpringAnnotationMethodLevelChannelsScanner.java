// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.common.MethodLevelAnnotationScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.payload.NamedSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationMethodLevelChannelsScanner<MethodAnnotation extends Annotation>
        extends MethodLevelAnnotationScanner<MethodAnnotation> implements SpringAnnotationChannelsScannerDelegator {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final PayloadService payloadService;

    public SpringAnnotationMethodLevelChannelsScanner(
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<MethodAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadService payloadService,
            ComponentsService componentsService) {
        super(bindingFactory, asyncHeadersBuilder, componentsService);
        this.methodAnnotationClass = methodAnnotationClass;
        this.payloadService = payloadService;
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        log.debug(
                "Scanning class \"{}\" for @\"{}\" annotated methods",
                clazz.getName(),
                methodAnnotationClass.getName());

        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationScannerUtil.findAnnotation(methodAnnotationClass, method) != null)
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelObject> mapMethodToChannel(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        MethodAnnotation annotation = AnnotationScannerUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        NamedSchemaObject payloadSchema = payloadService.extractSchema(method);
        ChannelObject channelItem = buildChannelItem(annotation, payloadSchema);

        String channelName = bindingFactory.getChannelName(annotation);
        return Map.entry(channelName, channelItem);
    }

    private ChannelObject buildChannelItem(MethodAnnotation annotation, NamedSchemaObject payloadSchema) {
        MessageObject message = buildMessage(annotation, payloadSchema);
        return buildChannelItem(annotation, message);
    }

    private ChannelObject buildChannelItem(MethodAnnotation annotation, MessageObject message) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(annotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        return ChannelObject.builder()
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(chBinding)
                .build();
    }
}
