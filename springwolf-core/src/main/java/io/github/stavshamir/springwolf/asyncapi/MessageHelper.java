// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.Message;
import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MessageHelper {
    private static final Comparator<MessageObject> byMessageName = Comparator.comparing(MessageObject::getName);

    private static final Supplier<Set<MessageObject>> messageSupplier = () -> new TreeSet<>(byMessageName);

    private MessageHelper() {}

    public static Map<String, Message> toMessagesMap(Set<MessageObject> messages) {
        if (messages.isEmpty()) {
            throw new IllegalArgumentException("messages must not be empty");
        }

        return new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier)))
                .stream().collect(Collectors.toMap(MessageObject::getMessageId, Function.identity()));
    }

    @SuppressWarnings("unchecked")
    public static Set<MessageObject> messageObjectToSet(Object messageObject) {
        if (messageObject instanceof MessageObject message) {
            return new HashSet<>(Collections.singletonList(message));
        }

        // FIXME
        // if (messageObject instanceof Map) {
        //     List<MessageObject> messages = ((Map<String, List<MessageObject>>) messageObject).get(ONE_OF);
        //     return new HashSet<>(messages);
        // }

        log.warn(
                "Message object must contain either a Message or a Map<String, Set<Message>, but contained: {}",
                messageObject.getClass());
        return new HashSet<>();
    }
}
