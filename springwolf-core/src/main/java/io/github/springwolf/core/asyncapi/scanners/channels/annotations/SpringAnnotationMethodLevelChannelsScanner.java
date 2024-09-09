// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.ReferenceUtil;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.SpringAnnotationMethodLevelScanner;
import io.github.springwolf.core.asyncapi.scanners.common.headers.AsyncHeadersBuilder;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.utils.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SpringAnnotationMethodLevelChannelsScanner<MethodAnnotation extends Annotation>
        extends SpringAnnotationMethodLevelScanner<MethodAnnotation> implements ChannelsInClassScanner {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final PayloadMethodService payloadMethodService;
    private final HeaderClassExtractor headerClassExtractor;

    public SpringAnnotationMethodLevelChannelsScanner(
            Class<MethodAnnotation> methodAnnotationClass,
            BindingFactory<MethodAnnotation> bindingFactory,
            AsyncHeadersBuilder asyncHeadersBuilder,
            PayloadMethodService payloadMethodService,
            HeaderClassExtractor headerClassExtractor,
            ComponentsService componentsService) {
        super(bindingFactory, asyncHeadersBuilder, componentsService);
        this.methodAnnotationClass = methodAnnotationClass;
        this.payloadMethodService = payloadMethodService;
        this.headerClassExtractor = headerClassExtractor;
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        return AnnotationScannerUtil.getRelevantMethods(clazz, methodAnnotationClass)
                .map(AnnotationScannerUtil.MethodAndAnnotation::method)
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelObject> mapMethodToChannel(Method method) {
        MethodAnnotation annotation = AnnotationUtil.findAnnotationOrThrow(methodAnnotationClass, method);

        PayloadSchemaObject payloadSchema = payloadMethodService.extractSchema(method);

        SchemaObject headerSchema = headerClassExtractor.extractHeader(method, payloadSchema);
        ChannelObject channelItem = buildChannelItem(annotation, payloadSchema, headerSchema);
        return Map.entry(channelItem.getChannelId(), channelItem);
    }

    private ChannelObject buildChannelItem(
            MethodAnnotation annotation, PayloadSchemaObject payloadSchema, SchemaObject headerSchema) {
        MessageObject message = buildMessage(annotation, payloadSchema, headerSchema);
        return buildChannelItem(annotation, message);
    }

    private ChannelObject buildChannelItem(MethodAnnotation annotation, MessageObject message) {
        Map<String, ChannelBinding> channelBinding = bindingFactory.buildChannelBinding(annotation);
        Map<String, ChannelBinding> chBinding = channelBinding != null ? new HashMap<>(channelBinding) : null;
        String channelName = bindingFactory.getChannelName(annotation);
        return ChannelObject.builder()
                .channelId(ReferenceUtil.toValidId(channelName))
                .address(channelName)
                .messages(Map.of(message.getMessageId(), MessageReference.toComponentMessage(message)))
                .bindings(chBinding)
                .build();
    }
}
