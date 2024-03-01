// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.operations;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;

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
