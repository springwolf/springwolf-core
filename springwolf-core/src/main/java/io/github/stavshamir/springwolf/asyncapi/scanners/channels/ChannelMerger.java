package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;

import java.util.*;
import java.util.function.Function;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;

public class ChannelMerger {

    public static Map<String, ChannelItem> merge(List<Map.Entry<String, ChannelItem>> channelEntries) {
        Map<String, ChannelItem> mergedChannels = new TreeMap<>();

        for (Map.Entry<String, ChannelItem> entry : channelEntries) {
            if (!mergedChannels.containsKey(entry.getKey())) {
                mergedChannels.put(entry.getKey(), entry.getValue());
            } else {
                ChannelItem channelItem = mergedChannels.get(entry.getKey());
                channelItem.setPublish(mergeOperation(channelItem, entry.getValue(), ChannelItem::getPublish));
                channelItem.setSubscribe(mergeOperation(channelItem, entry.getValue(), ChannelItem::getSubscribe));
            }
        }

        return mergedChannels;
    }

    private static Operation mergeOperation(ChannelItem channelItem, ChannelItem otherChannelItem, Function<ChannelItem, Operation> getOperation) {
        Set<Message> mergedMessages = getChannelMessages(channelItem, getOperation);
        Set<Message> currentEntryMessages = getChannelMessages(otherChannelItem, getOperation);
        mergedMessages.addAll(currentEntryMessages);

        Operation operation = getOperation.apply(channelItem) != null ? getOperation.apply(channelItem) : getOperation.apply(otherChannelItem);
        if (!mergedMessages.isEmpty()) {
            operation.setMessage(toMessageObjectOrComposition(mergedMessages));
        }
        return operation;
    }

    private static Set<Message> getChannelMessages(ChannelItem channelItem, Function<ChannelItem, Operation> getOperation) {
        return Optional
                .ofNullable(getOperation.apply(channelItem))
                .map(Operation::getMessage)
                .map(MessageHelper::messageObjectToSet)
                .orElseGet(TreeSet::new);
    }
}
