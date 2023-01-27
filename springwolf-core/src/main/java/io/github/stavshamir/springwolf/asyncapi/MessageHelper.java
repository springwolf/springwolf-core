package io.github.stavshamir.springwolf.asyncapi;

import com.google.common.collect.ImmutableMap;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public class MessageHelper {
    private static final String ONE_OF = "oneOf";

    private static final Comparator<Message> byMessageName = Comparator.comparing(Message::getName);
    private static final Supplier<Set<Message>> messageSupplier =
            () -> new TreeSet<>(byMessageName);

    public static Object toMessageObjectOrComposition(Set<Message> messages) {
        switch (messages.size()) {
            case 0:
                throw new IllegalArgumentException("messages must not be empty");
            case 1:
                return messages.toArray()[0];
            default:
                return ImmutableMap.of(
                        ONE_OF,
                        messages.stream().collect(Collectors.toCollection(messageSupplier)));
        }
    }

    @SuppressWarnings("unchecked")
    public static Set<Message> messageObjectToSet(Object messageObject) {
        if (messageObject instanceof Message) {
            return new HashSet<>(Arrays.asList((Message) messageObject));
        }

        if (messageObject instanceof Map) {
            Set<Message> messages = ((Map<String, Set<Message>>) messageObject).get(ONE_OF);
            return new HashSet<>(messages);
        }

        log.warn(
                "Message object must contain either a Message or a Map<String, Set<Message>, but contained: {}",
                messageObject.getClass());
        return new HashSet<>();
    }
}
