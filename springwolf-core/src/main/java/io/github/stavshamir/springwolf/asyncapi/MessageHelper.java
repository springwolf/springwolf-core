// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.v3.model.channel.message.MessageObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MessageHelper {
    private static final String ONE_OF = "oneOf";

    private static final Comparator<MessageObject> byMessageName = Comparator.comparing(MessageObject::getName);

    private static final Supplier<Set<MessageObject>> messageSupplier = () -> new TreeSet<>(byMessageName);

    public static Object toMessageObjectOrComposition(Set<MessageObject> messages) {
        return switch (messages.size()) {
            case 0 -> throw new IllegalArgumentException("messages must not be empty");
            case 1 -> messages.toArray()[0];
            default -> Map.of(
                    ONE_OF, new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier))));
        };
    }

    @SuppressWarnings("unchecked")
    public static Set<MessageObject> messageObjectToSet(Object messageObject) {
        if (messageObject instanceof MessageObject message) {
            return new HashSet<>(Collections.singletonList(message));
        }

        if (messageObject instanceof Map) {
            List<MessageObject> messages = ((Map<String, List<MessageObject>>) messageObject).get(ONE_OF);
            return new HashSet<>(messages);
        }

        log.warn(
                "Message object must contain either a Message or a Map<String, Set<Message>, but contained: {}",
                messageObject.getClass());
        return new HashSet<>();
    }
}
