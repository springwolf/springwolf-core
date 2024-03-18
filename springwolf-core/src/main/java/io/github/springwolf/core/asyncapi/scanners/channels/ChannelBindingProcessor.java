// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import java.lang.reflect.Method;
import java.util.Optional;

public interface ChannelBindingProcessor {

    /**
     * Process the methods annotated with Channel Binding Annotation
     * for protocol specific channelBinding annotations, method parameters, etc
     *
     * @param method The method being annotated
     * @return A message binding, if found
     */
    Optional<ProcessedChannelBinding> process(Method method);
}
