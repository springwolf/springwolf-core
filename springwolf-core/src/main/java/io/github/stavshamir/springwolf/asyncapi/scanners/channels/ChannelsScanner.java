// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.Map;

public interface ChannelsScanner {

    /**
     * @return A mapping of channel names to their respective channel object for a given protocol.
     */
    Map<String, ChannelObject> scanChannels();

    /**
     * @return A mapping of operation names to their respective operation object for a given protocol.
     */
    Map<String, Operation> scanOperations();
}
