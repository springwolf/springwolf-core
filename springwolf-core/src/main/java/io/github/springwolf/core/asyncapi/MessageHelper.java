// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi;

import io.github.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import io.github.springwolf.asyncapi.v3.model.channel.message.MessageReference;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MessageHelper {
    private static final Comparator<MessageObject> byMessageName = Comparator.comparing(MessageObject::getName);

    private static final Supplier<Set<MessageObject>> messageSupplier = () -> new TreeSet<>(byMessageName);

    private MessageHelper() {}

    public static Map<String, MessageReference> toMessagesMap(Set<MessageObject> messages) {
        if (messages.isEmpty()) {
            throw new IllegalArgumentException("messages must not be empty");
        }

        return new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier)))
                .stream().collect(Collectors.toMap(MessageObject::getName, MessageReference::toComponentMessage));
    }

    public static Map<String, MessageReference> toOperationsMessagesMap(
            String channelName, Set<MessageObject> messages) {
        if (channelName == null || channelName.isBlank()) {
            throw new IllegalArgumentException("channelName must not be empty");
        }

        if (messages.isEmpty()) {
            throw new IllegalArgumentException("messages must not be empty");
        }

        return new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier)))
                .stream()
                        .collect(Collectors.toMap(
                                MessageObject::getName,
                                e -> MessageReference.toChannelMessage(channelName, e.getName())));
    }
}
