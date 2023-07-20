package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AsyncOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.OperationBindingProcessor;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Optional;

public abstract class AbstractOperationBindingProcessor<A>
        implements OperationBindingProcessor, EmbeddedValueResolverAware {
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        final Class<A> clazz = getGenericAnnotationClass();

        return Arrays.stream(method.getAnnotations())
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(AsyncOperationBinding.class))
                .map(clazz::cast)
                .findAny()
                .map(this::mapToOperationBinding);
    }

    private Class<A> getGenericAnnotationClass() {
        return (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract ProcessedOperationBinding mapToOperationBinding(A bindingAnnotation);

    protected String resolveOrNull(String stringValue) {
        return StringUtils.hasText(stringValue) ? resolver.resolveStringValue(stringValue) : null;
    }
}
