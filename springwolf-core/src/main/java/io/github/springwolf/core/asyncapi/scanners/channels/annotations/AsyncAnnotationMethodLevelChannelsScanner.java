// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationMethodLevelChannelsScanner<MethodAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final AsyncAnnotationProvider<MethodAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationChannelService<MethodAnnotation> asyncAnnotationChannelService;

    @Override
    public List<ChannelObject> scan(Class<?> clazz) {
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, this.asyncAnnotationProvider.getAnnotation())
                .map(this::mapMethodToChannel)
                .toList();
    }

    private ChannelObject mapMethodToChannel(MethodAndAnnotation<MethodAnnotation> methodAndAnnotation) {
        return this.asyncAnnotationChannelService.buildChannel(methodAndAnnotation);
    }
}
