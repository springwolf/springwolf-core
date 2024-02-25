// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings;

import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.springwolf.core.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;

import java.lang.reflect.Method;
import java.util.Optional;

public interface MessageBindingProcessor {

    /**
     * Process the methods annotated with {@link AsyncPublisher} and {@link AsyncListener}
     * for protocol specific messageBinding annotations, method parameters, etc
     *
     * @param method The method being annotated
     * @return A message binding, if found
     */
    Optional<ProcessedMessageBinding> process(Method method);
}
