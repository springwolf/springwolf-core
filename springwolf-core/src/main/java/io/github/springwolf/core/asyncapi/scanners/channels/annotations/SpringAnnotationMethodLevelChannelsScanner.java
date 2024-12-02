// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.SpringAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.headers.HeaderClassExtractor;
import io.github.springwolf.core.asyncapi.scanners.common.message.SpringAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadMethodService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadSchemaObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class SpringAnnotationMethodLevelChannelsScanner<MethodAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final Class<MethodAnnotation> methodAnnotationClass;
    private final PayloadMethodService payloadMethodService;
    private final HeaderClassExtractor headerClassExtractor;
    private final SpringAnnotationChannelService<MethodAnnotation> springAnnotationChannelService;
    private final SpringAnnotationMessageService<MethodAnnotation> springAnnotationMessageService;

    @Override
    public List<ChannelObject> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, methodAnnotationClass)
                .map(this::mapMethodToChannel)
                .toList();
    }

    private ChannelObject mapMethodToChannel(MethodAndAnnotation<MethodAnnotation> method) {
        MethodAnnotation annotation = AnnotationUtil.findFirstAnnotationOrThrow(methodAnnotationClass, method.method());

        PayloadSchemaObject payloadSchema = payloadMethodService.extractSchema(method.method());
        SchemaObject headerSchema = headerClassExtractor.extractHeader(method.method(), payloadSchema);
        MessageObject message = springAnnotationMessageService.buildMessage(annotation, payloadSchema, headerSchema);
        Map<String, Message> messages = Map.of(message.getMessageId(), MessageReference.toComponentMessage(message));

        BindingContext bindingContext = BindingContext.ofAnnotatedMethod(method.method());

        return springAnnotationChannelService.buildChannel(annotation, bindingContext, messages);
    }
}
