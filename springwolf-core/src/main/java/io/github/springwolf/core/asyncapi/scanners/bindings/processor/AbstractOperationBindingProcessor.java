// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.processor;

import io.github.springwolf.core.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.springwolf.core.asyncapi.scanners.bindings.ProcessedOperationBinding;
import io.github.springwolf.core.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public abstract class AbstractOperationBindingProcessor<A>
        implements OperationBindingProcessor, EmbeddedValueResolverAware {

    private final Class<A> specificAnnotationClazz =
            (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(AsyncOperationBinding.class))
                .flatMap(this::tryCast)
                .findAny()
                .map(this::mapToOperationBinding);
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

    protected abstract ProcessedOperationBinding mapToOperationBinding(A bindingAnnotation);

    protected String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? resolver.resolveStringValue(stringValue) : null;
    }
}
