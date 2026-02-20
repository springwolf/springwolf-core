// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common.channel.inferrer;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Strategy interface for inferring an AsyncAPI channel name from a listener method when
 * no explicit {@code channelName} has been provided via {@code @AsyncOperation}.
 * <p>
 * Implementations are protocol-specific (e.g. AMQP, Kafka) and should inspect the
 * broker-listener annotation present on the method to derive a meaningful channel name.
 * <p>
 * Multiple implementations may be registered; the first non-empty result wins.
 */
public interface ChannelNameInferrer {

    /**
     * Attempt to infer a channel name from the given method.
     *
     * @param method the listener method being processed
     * @return an {@link Optional} containing the inferred channel name, or empty if this
     *         inferrer cannot determine one from the method
     */
    Optional<String> inferChannelName(Method method);
}
