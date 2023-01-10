package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedMessageBinding;

import java.lang.reflect.Method;
import java.util.Optional;

public interface MessageBindingProcessor {

    /**
     * Process the methods annotated with {@link AsyncPublisher} and {@link AsyncSubscriber}
     * for protocol specific messageBinding annotations, method parameters, etc
     *
     * @param method The method being annotated
     * @return A message binding, if found
     */
    Optional<ProcessedMessageBinding> process(Method method);
}
