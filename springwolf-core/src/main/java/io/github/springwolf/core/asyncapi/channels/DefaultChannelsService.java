// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.channels;

import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.core.asyncapi.scanners.channels.ChannelMerger;
import io.github.springwolf.core.asyncapi.scanners.ChannelsScanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service to detect AsyncAPI channels in the current spring context.
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultChannelsService implements ChannelsService {

    private final List<? extends ChannelsScanner> channelsScanners;

    /**
     * Collects all AsyncAPI ChannelObjects using the available {@link ChannelsScanner}
     * beans.
     * @return Map of channel names mapping to detected ChannelObject
     */
    @Override
    public Map<String, ChannelObject> findChannels() {
        List<Map.Entry<String, ChannelObject>> foundChannelItems = new ArrayList<>();

        for (ChannelsScanner scanner : channelsScanners) {
            try {
                Map<String, ChannelObject> channels = scanner.scan();
                foundChannelItems.addAll(channels.entrySet());
            } catch (Exception e) {
                log.error("An error was encountered during channel scanning with {}: {}", scanner, e.getMessage(), e);
            }
        }
        return ChannelMerger.mergeChannels(foundChannelItems);
    }
}
