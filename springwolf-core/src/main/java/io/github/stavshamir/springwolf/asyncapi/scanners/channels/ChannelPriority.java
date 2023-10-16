// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;

public class ChannelPriority {
    /**
     * Manual defined channels have the highest priority
     * <p>
     * Example: Definition via {@link AsyncApiDocket}
     */
    public static final int MANUAL_DEFINED = 1;

    /**
     * Definition via custom annotations
     * <p>
     * Example: {@link AsyncPublisher}, {@link AsyncListener}
     */
    public static final int ASYNC_ANNOTATION = 2;

    /**
     * Definitions found via spring listener annotations are used last.
     * <p>
     * Examples: Plugins like KafkaListener, etc
     */
    public static final int AUTO_DISCOVERED = 3;
}
