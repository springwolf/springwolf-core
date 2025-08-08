// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.channels;

import io.github.springwolf.core.asyncapi.annotations.AsyncChannelBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractChannelBindingProcessor<A> implements ChannelBindingProcessor {

    private final StringValueResolver stringValueResolver;

    private final Class<A> specificAnnotationClazz =
            (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public Optional<ProcessedChannelBinding> process(AnnotatedElement annotatedElement) {
        return Arrays.stream(annotatedElement.getAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(AsyncChannelBinding.class))
                .flatMap(this::tryCast)
                .findAny()
                .map(this::mapToChannelBinding);
    }

    /**
     * Attempt to cast the annotation to the specific annotation
     * <p>
     * Casting might fail, when multiple, different binding annotations are used,
     * which results in an (expected) exception.
     * <p>
     * If there is an option to previously test casting without casting, then lets change the code here.
     */
    private Stream<A> tryCast(Annotation obj) {
        try {
            return Stream.of(specificAnnotationClazz.cast(obj));
        } catch (ClassCastException ex) {
            log.trace("Method has multiple bindings defined.", ex);
        }
        return Stream.empty();
    }

    protected abstract ProcessedChannelBinding mapToChannelBinding(A bindingAnnotation);

    protected String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? stringValueResolver.resolveStringValue(stringValue) : null;
    }
}
