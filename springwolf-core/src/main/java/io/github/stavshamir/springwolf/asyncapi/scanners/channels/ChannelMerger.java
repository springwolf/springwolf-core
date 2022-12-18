package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import io.github.stavshamir.springwolf.asyncapi.MessageHelper;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;

import java.util.*;

import static io.github.stavshamir.springwolf.asyncapi.MessageHelper.toMessageObjectOrComposition;


/**
 * Util to merge multiple {@link ChannelItem}s
 */
public class ChannelMerger {

    /**
     * Merges multiple channelItems by channel name
     * <p>
     * Given two channelItems for the same channel name, the first seen ChannelItem is used
     * If an operation is null, the next non-null operation is used
     * Messages within operations are merged
     *
     * @param channelEntries Ordered pairs of channel name to ChannelItem
     * @return A map of channelName to a single ChannelItem
     */
    public static Map<String, ChannelItem> merge(List<Map.Entry<String, ChannelItem>> channelEntries) {
        Map<String, ChannelItem> mergedChannels = new TreeMap<>();

        for (Map.Entry<String, ChannelItem> entry : channelEntries) {
            if (!mergedChannels.containsKey(entry.getKey())) {
                mergedChannels.put(entry.getKey(), entry.getValue());
            } else {
                ChannelItem channelItem = mergedChannels.get(entry.getKey());
                channelItem.setPublish(mergeOperation(channelItem.getPublish(), entry.getValue().getPublish()));
                channelItem.setSubscribe(mergeOperation(channelItem.getSubscribe(), entry.getValue().getSubscribe()));
            }
        }

        return mergedChannels;
    }

    private static Operation mergeOperation(Operation operation, Operation otherOperation) {
        Set<Message> mergedMessages = getMessages(operation);
        Set<Message> currentEntryMessages = getMessages(otherOperation);
        mergedMessages.addAll(currentEntryMessages);

        Operation mergedOperation = operation != null ? operation : otherOperation;
        if (!mergedMessages.isEmpty()) {
            mergedOperation.setMessage(toMessageObjectOrComposition(mergedMessages));
        }
        return mergedOperation;
    }

    private static Set<Message> getMessages(Operation operation) {
        return Optional
                .ofNullable(operation)
                .map(Operation::getMessage)
                .map(MessageHelper::messageObjectToSet)
                .orElseGet(TreeSet::new);
    }
}
