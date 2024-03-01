// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;

import java.util.Map;

public interface ChannelsScanner {

    /**
     * @return A mapping of channel names to their respective channel object for a given protocol.
     */
    Map<String, ChannelObject> scan();
}
