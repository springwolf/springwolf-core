// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.bindings.channels;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public interface ChannelBindingProcessor {

    /**
     * Process the elements annotated with Channel Binding Annotation
     * for protocol specific channelBinding annotations, method parameters, etc
     *
     * @param annotatedElement The element being annotated
     * @return A message binding, if found
     */
    Optional<ProcessedChannelBinding> process(AnnotatedElement annotatedElement);
}
