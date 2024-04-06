// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.messages;

import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public interface MessageBindingProcessor {

    /**
     * Process the methods annotated with {@link AsyncPublisher} and {@link AsyncListener}
     * for protocol specific messageBinding annotations, annotatedElement parameters, etc
     *
     * @param annotatedElement The element being annotated
     * @return A message binding, if found
     */
    Optional<ProcessedMessageBinding> process(AnnotatedElement annotatedElement);
}
