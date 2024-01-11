// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.Channel;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.ChannelObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.stavshamir.springwolf.asyncapi.v3.model.operation.Operation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessagesMap;

/**
 * Util to merge multiple {@link Channel}s
 */
public class ChannelMerger {

    private ChannelMerger() {}

    /**
     * Merges multiple channels by channel name
     * <p>
     * Given two channels for the same channel name, the first seen Channel is used
     * If an operation is null, the next non-null operation is used
     * Messages within operations are merged
     *
     * @param channelEntries Ordered pairs of channel name to Channel
     * @return A map of channelName to a single Channel
     */
    public static Map<String, ChannelObject> merge(List<Map.Entry<String, ChannelObject>> channelEntries) {
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

        Set<MessageObject> mergedMessages = mergeMessages(getMessages(channel), getMessages(otherChannel));
        if (!mergedMessages.isEmpty()) {
            mergedChannel.setMessages(toMessagesMap(mergedMessages));
        }
        return mergedChannel;
    }

    private static Operation mergeOperation(Operation operation, Operation otherOperation) {
        Operation mergedOperation = operation != null ? operation : otherOperation;

        // FIXME
        // Set<MessageObject> mergedMessages = mergeMessages(getMessages(operation), getMessages(otherOperation));
        //        if (!mergedMessages.isEmpty()) {
        //            mergedOperation.setMessage(toMessageObjectOrComposition(mergedMessages));
        //        }
        return mergedOperation;
    }

    private static Set<MessageObject> mergeMessages(Set<Message> messages, Set<Message> otherMessages) {
        // FIXME: We will lose any MessageReference we get
        Map<String, MessageObject> nameToMessage = messages.stream()
                .filter(MessageObject.class::isInstance)
                .map(MessageObject.class::cast)
                .collect(Collectors.toMap(MessageObject::getName, Function.identity()));

        for (Message otherMessage : otherMessages) {
            if (otherMessage instanceof MessageObject otherMessageObject) {
                nameToMessage.putIfAbsent(otherMessageObject.getName(), otherMessageObject);
            }
        }

        return new HashSet<>(nameToMessage.values());
    }

    // FIXME
    //    private static Set<Message> getMessages(Operation operation) {
    //        return Optional.ofNullable(operation)
    //                .map(Operation::getMessages)
    //                .map(MessageHelper::messageObjectToSet)
    //                .orElseGet(HashSet::new);
    //    }

    private static Set<Message> getMessages(ChannelObject channel) {
        return Optional.ofNullable(channel)
                .map(ChannelObject::getMessages)
                .map(MessageHelper::messageObjectToSet)
                .orElseGet(HashSet::new);
    }
}
