// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.common;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MessageHelper {
    private static final Comparator<MessageObject> byMessageId = Comparator.comparing(MessageObject::getMessageId);

    private static final Supplier<Set<MessageObject>> messageSupplier = () -> new TreeSet<>(byMessageId);

    private MessageHelper() {}

    public static Map<String, MessageReference> toMessagesMap(Set<MessageObject> messages) {
        Function<MessageObject, MessageReference> aggregator = MessageReference::toComponentMessage;
        return toMessageReferences(messages, aggregator);
    }

    public static Map<String, MessageReference> toOperationsMessagesMap(String channelId, Set<MessageObject> messages) {
        if (channelId == null || channelId.isBlank()) {
            throw new IllegalArgumentException("channelId must not be empty");
        }

        Function<MessageObject, MessageReference> aggregator =
                (message) -> MessageReference.toChannelMessage(channelId, message.getMessageId());
        return toMessageReferences(messages, aggregator);
    }

    private static Map<String, MessageReference> toMessageReferences(
            Set<MessageObject> messages, Function<MessageObject, MessageReference> messageReferenceFunction) {
        if (messages.isEmpty()) {
            throw new IllegalArgumentException("messages must not be empty");
        }

        return new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier)))
                .stream().collect(Collectors.toMap(MessageObject::getMessageId, messageReferenceFunction));
    }
}
