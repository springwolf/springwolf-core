package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;

import java.lang.reflect.Method;
import java.util.Optional;

public interface OperationBindingProcessor {

    /**
     * Process the methods annotated with {@link AsyncPublisher} and {@link AsyncListener}
     * for protocol specific operationBinding annotations, method parameters, etc
     *
     * @param method The method being annotated
     * @return An operation binding, if found
     */
    Optional<ProcessedOperationBinding> process(Method method);
}
