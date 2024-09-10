// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.components.ComponentsService;
import io.github.springwolf.core.asyncapi.scanners.bindings.messages.MessageBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.operations.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
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
public class AsyncAnnotationMethodLevelChannelsScanner<MethodAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationChannelService<MethodAnnotation> asyncAnnotationChannelService;

    public AsyncAnnotationMethodLevelChannelsScanner(
            AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider,
            ComponentsService componentsService,
            AsyncApiDocketService asyncApiDocketService,
            PayloadAsyncOperationService payloadAsyncOperationService,
            List<OperationBindingProcessor> operationBindingProcessors,
            List<MessageBindingProcessor> messageBindingProcessors,
            StringValueResolverProxy resolver) {
        this.asyncAnnotationProvider = asyncAnnotationProvider;
        AsyncAnnotationMessageService asyncAnnotationMessageService = new AsyncAnnotationMessageService(
                payloadAsyncOperationService, componentsService, messageBindingProcessors, resolver);
        AsyncAnnotationOperationService<MethodAnnotation> asyncAnnotationOperationService =
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
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, this.asyncAnnotationProvider.getAnnotation())
                .map(this::mapMethodToChannel);
    }

    private Map.Entry<String, ChannelObject> mapMethodToChannel(
            MethodAndAnnotation<MethodAnnotation> methodAndAnnotation) {
        ChannelObject channel = this.asyncAnnotationChannelService.buildChannel(methodAndAnnotation);
        return Map.entry(channel.getChannelId(), channel);
    }
}
