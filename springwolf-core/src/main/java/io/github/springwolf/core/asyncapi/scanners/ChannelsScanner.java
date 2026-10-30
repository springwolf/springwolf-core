// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;

import java.util.List;

public interface ChannelsScanner {

    /**
     * @return The channel objects found for a given protocol.
     */
    List<ChannelObject> scan();
}
