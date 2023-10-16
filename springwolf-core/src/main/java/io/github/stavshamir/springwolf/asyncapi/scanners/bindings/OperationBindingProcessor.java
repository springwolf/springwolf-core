// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;

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
