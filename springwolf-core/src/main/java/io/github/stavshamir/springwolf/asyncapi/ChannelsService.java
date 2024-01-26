// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;

import java.util.Map;

/**
 * Service to detect AsyncAPI channels in the current spring context.
 */
public interface ChannelsService {

    /**
     * Detects all available AsyncAPI ChannelObject in the spring context.
     *
     * @return Map of channel names mapping to detected ChannelItems
     */
    Map<String, ChannelObject> findChannels();
}
