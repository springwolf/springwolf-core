// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util to merge multiple {@link Channel}s
 */
public class ChannelMerger {

    private ChannelMerger() {}

    /**
     * Merges multiple channels by channel name
     * <p>
     * Given two channels for the same channel name, the first seen Channel is used
     * Messages within channels are merged
     *
     * @param channelEntries Ordered pairs of channel name to Channel
     * @return A map of channelName to a single Channel
     */
    public static Map<String, ChannelObject> mergeChannels(List<Map.Entry<String, ChannelObject>> channelEntries) {
        Map<String, ChannelObject> mergedChannels = new HashMap<>();

        for (Map.Entry<String, ChannelObject> entry : channelEntries) {
            if (!mergedChannels.containsKey(entry.getKey())) {
                mergedChannels.put(entry.getKey(), entry.getValue());
            } else {
                ChannelObject channel = mergeChannel(mergedChannels.get(entry.getKey()), entry.getValue());
                mergedChannels.put(entry.getKey(), channel);
            }
        }

        return mergedChannels;
    }

    private static ChannelObject mergeChannel(ChannelObject channel, ChannelObject otherChannel) {
        ChannelObject mergedChannel = channel != null ? channel : otherChannel;

        Map<String, Message> channelMessages = channel.getMessages();
        Map<String, Message> otherChannelMessages = otherChannel.getMessages();

        Map<String, Message> mergedMessages = new HashMap<>();
        if (channelMessages != null) {
            mergedMessages.putAll(channelMessages);
        }
        if (otherChannelMessages != null) {
            otherChannelMessages.forEach(mergedMessages::putIfAbsent);
        }

        if (!mergedMessages.isEmpty()) {
            mergedChannel.setMessages(mergedMessages);
        }

        return mergedChannel;
    }
}
