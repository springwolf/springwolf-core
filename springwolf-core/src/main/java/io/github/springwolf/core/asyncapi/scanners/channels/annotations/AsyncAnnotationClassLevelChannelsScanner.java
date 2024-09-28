// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels.annotations;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelsInClassScanner;
import io.github.springwolf.core.asyncapi.scanners.common.AsyncAnnotationProvider;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AllMethods;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationScannerUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.AnnotationUtil;
import io.github.springwolf.core.asyncapi.scanners.common.annotation.MethodAndAnnotation;
import io.github.springwolf.core.asyncapi.scanners.common.channel.AsyncAnnotationChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AsyncAnnotationClassLevelChannelsScanner<ClassAnnotation extends Annotation>
        implements ChannelsInClassScanner {

    private final AsyncAnnotationProvider<ClassAnnotation> asyncAnnotationProvider;
    private final AsyncAnnotationChannelService<ClassAnnotation> asyncAnnotationChannelService;

    @Override
    public List<ChannelObject> scan(Class<?> clazz) {
        Class<ClassAnnotation> annotation = this.asyncAnnotationProvider.getAnnotation();
        return AnnotationScannerUtil.findAnnotatedMethods(clazz, annotation, AllMethods.class, (cl, m) -> {
                    ClassAnnotation classAnnotation = AnnotationUtil.findFirstAnnotation(annotation, clazz);
                    return m.stream().map(method -> new MethodAndAnnotation<>(method.method(), classAnnotation));
                })
                .map(this::mapMethodToChannel)
                .toList();
    }

    private ChannelObject mapMethodToChannel(MethodAndAnnotation<ClassAnnotation> methodAndAnnotation) {
        return this.asyncAnnotationChannelService.buildChannel(methodAndAnnotation);
    }
}
