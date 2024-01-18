// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.bindings;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

/**
 * Order of binding processors
 */
public class BindingProcessorPriority {
    /**
     * Manual defined binding processors have the highest priority
     * <p>
     * Example: Definition via {@link AsyncApiDocket}
     */
    public static final int MANUAL_DEFINED = 1;

    /**
     * Definition via generic annotation
     * <p>
     * to extend and overwrite the protocol binding - which might not support all features
     * <p>
     * Example: AsyncGenericOperationBinding
     */
    public static final int GENERIC_BINDING = 2;

    /**
     * Protocol specific bindings, which try to auto-detect all fields
     * <p>
     * Examples: Plugins like KafkaOperationBindingProcessor, etc
     */
    public static final int PROTOCOL_BINDING = 3;

    private BindingProcessorPriority() {}
}
