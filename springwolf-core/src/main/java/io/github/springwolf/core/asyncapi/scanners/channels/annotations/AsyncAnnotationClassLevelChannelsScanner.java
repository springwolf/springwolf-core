// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AllMethods;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import io.github.springwolf.core.asyncapi.scanners.common.message.AsyncAnnotationMessageService;
import io.github.springwolf.core.asyncapi.scanners.common.operation.AsyncAnnotationOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadAsyncOperationService;
import io.github.springwolf.core.asyncapi.scanners.common.utils.StringValueResolverProxy;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class AsyncAnnotationClassLevelChannelsScanner<ClassAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final AsyncAnnotationProvider<ClassAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationChannelService<ClassAnnotation> asyncAnnotationChannelService;

    public AsyncAnnotationClassLevelChannelsScanner(
            AsyncAnnotationProvider<ClassAnnotation> asyncAnnotationProvider,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadAsyncOperationService payloadAsyncOperationService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy resolver) {
        this.asyncAnnotationProvider = asyncAnnotationProvider;
        AsyncAnnotationMessageService asyncAnnotationMessageService = new AsyncAnnotationMessageService(
                payloadAsyncOperationService, componentsService, messageBindingProcessors, resolver);
        AsyncAnnotationOperationService<ClassAnnotation> asyncAnnotationOperationService =
                new AsyncAnnotationOperationService<>(
                        asyncAnnotationProvider, operationBindingProcessors, resolver, asyncAnnotationMessageService);
        this.asyncAnnotationChannelService = new AsyncAnnotationChannelService<>(
                asyncAnnotationProvider,
                asyncAnnotationOperationService,
                asyncAnnotationMessageService,
                resolver,
                asyncApiDocketService);
    }

    @Override
    public Stream<Map.Entry<String, ChannelObject>> scan(Class<?> clazz) {
        Class<ClassAnnotation> annotation = this.asyncAnnotationProvider.getAnnotation();
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, annotation, AllMethods.class, (cl, m) -> {
                    ClassAnnotation classAnnotation = AnnotationUtil.findAnnotation(annotation, clazz);
                    return m.stream().map(method -> new MethodAndAnnotation<>(method.method(), classAnnotation));
                })
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelObject> mapMethodToChannel(
            MethodAndAnnotation<ClassAnnotation> methodAndAnnotation) {
        ChannelObject channel = this.asyncAnnotationChannelService.buildChannel(methodAndAnnotation);
        return Map.entry(channel.getChannelId(), channel);
    }
}
