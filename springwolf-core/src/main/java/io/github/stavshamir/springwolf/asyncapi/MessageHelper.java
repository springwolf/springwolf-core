package io.github.stavshamir.springwolf.asyncapi;

import com.google.common.collect.ImmutableMap;
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

    // TODO Why do we need a SortedSet here? Using a comparator with only the message name will break deep equals on the Set
    // Unfortunately there are Tests relying on deep equals
    // see https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html
    private static final Supplier<Set<Message>> messageSupplier = () -> new TreeSet<>(byMessageName);

    public static Object toMessageObjectOrComposition(Set<Message> messages) {
        switch (messages.size()) {
            case 0:
                throw new IllegalArgumentException("messages must not be empty");
            case 1:
                return messages.toArray()[0];
            default:
                return ImmutableMap.of(ONE_OF, new ArrayList<>(messages.stream().collect(Collectors.toCollection(messageSupplier))));
        }
    }

    @SuppressWarnings("unchecked")
    public static Set<Message> messageObjectToSet(Object messageObject) {
        if (messageObject instanceof Message) {
            return new HashSet<>(Collections.singletonList((Message) messageObject));
        }

        if (messageObject instanceof Map) {
            List<Message> messages = ((Map<String, List<Message>>) messageObject).get(ONE_OF);
            return new HashSet<>(messages);
        }

        log.warn("Message object must contain either a Message or a Map<String, Set<Message>, but contained: {}", messageObject.getClass());
        return new HashSet<>();
    }
}
