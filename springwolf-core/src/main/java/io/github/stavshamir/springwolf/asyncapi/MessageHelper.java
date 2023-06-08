package io.github.stavshamir.springwolf.asyncapi;

import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
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

    private static final Comparator<Message> byMessageName = Comparator.comparing(Message::getName);

    private static final Supplier<Set<Message>> messageSupplier = () -> new TreeSet<>(byMessageName);

    public static Object toMessageObjectOrComposition(Set<Message> messages) {
        return switch (messages.size()) {
            case 0 -> throw new IllegalArgumentException("messages must not be empty");
            case 1 -> messages.toArray()[0];
            default ->
                    Map.of(ONE_OF, new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier))));
        };
    }

    @SuppressWarnings("unchecked")
    public static Set<Message> messageObjectToSet(Object messageObject) {
        if (messageObject instanceof Message message) {
            return new HashSet<>(Collections.singletonList(message));
        }

        if (messageObject instanceof Map) {
            List<Message> messages = ((Map<String, List<Message>>) messageObject).get(ONE_OF);
            return new HashSet<>(messages);
        }

        log.warn("Message object must contain either a Message or a Map<String, Set<Message>, but contained: {}", messageObject.getClass());
        return new HashSet<>();
    }
}
