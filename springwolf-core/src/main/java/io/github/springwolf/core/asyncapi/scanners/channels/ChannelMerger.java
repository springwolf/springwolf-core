// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.channels;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.model.channel.Channel;
import io.github.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util to merge multiple {@link Channel}s
 */
public class ChannelMerger {

    private ChannelMerger() {}

    /**
     * Merges multiple channels
     * <p>
     * Given two channels for the same channel id, the first seen Channel is used
     * Messages within channels are merged
     *
     * @return A map of channelId to a single Channel
     */
    public static Map<String, ChannelObject> mergeChannels(List<ChannelObject> channels) {
        Map<String, ChannelObject> mergedChannels = new HashMap<>();

        for (ChannelObject channel : channels) {
            if (!mergedChannels.containsKey(channel.getChannelId())) {
                mergedChannels.put(channel.getChannelId(), channel);
            } else {
                ChannelObject existingChannel = mergedChannels.get(channel.getChannelId());
                ChannelObject mergedChannel = mergeChannel(existingChannel, channel);
                mergedChannels.put(mergedChannel.getChannelId(), mergedChannel);
            }
        }

        return mergedChannels;
    }

    private static ChannelObject mergeChannel(ChannelObject channel, ChannelObject otherChannel) {
        ChannelObject mergedChannel = channel != null ? channel : otherChannel;

        mergeMessages(channel, otherChannel, mergedChannel);
        mergeBindings(channel, otherChannel, mergedChannel);

        return mergedChannel;
    }

    private static void mergeMessages(ChannelObject channel, ChannelObject otherChannel, ChannelObject mergedChannel) {
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
    }

    private static void mergeBindings(ChannelObject channel, ChannelObject otherChannel, ChannelObject mergedChannel) {
        Map<String, ChannelBinding> channelBindings = channel.getBindings();
        Map<String, ChannelBinding> otherChannelBindings = otherChannel.getBindings();

        Map<String, ChannelBinding> mergedBindings = new HashMap<>();
        if (channelBindings != null) {
            mergedBindings.putAll(channelBindings);
        }
        if (otherChannelBindings != null) {
            otherChannelBindings.forEach(mergedBindings::putIfAbsent);
        }

        if (!mergedBindings.isEmpty()) {
            mergedChannel.setBindings(mergedBindings);
        }
    }
}
